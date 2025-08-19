package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.CustomException.UnauthorizedException;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility service for student-related operations
 */
@Service
public class StudentUtilityService {

    private static final Logger logger = LoggerFactory.getLogger(StudentUtilityService.class);

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private BranchRepo branchRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepo userRoleRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private StandardRepo standardRepository;

    @Autowired
    private BoardRepo boardRepository;


    /**
     * Get student statistics by branch
     */
    public Map<String, Object> getStudentStatsByBranch(Long branchId) {
        Map<String, Object> stats = new HashMap<>();

        Long totalStudents = studentRepository.countActiveStudentsByBranch(branchId);
        Long approvedStudents = studentRepository.countApprovedStudentsByBranch(branchId);

        stats.put("totalActiveStudents", totalStudents);
        stats.put("approvedStudents", approvedStudents);
        stats.put("pendingApproval", totalStudents - approvedStudents);
        stats.put("branchId", branchId);

        return stats;
    }

    /**
     * Get students by batch year and branch
     */
    public List<Student> getStudentsByBatchAndBranch(Integer batchYear, Long branchId) {
        return studentRepository.findByBatchYearAndBranchBranchId(batchYear, branchId);
    }

    /**
     * Search students by name
     */
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByStudentNameContainingIgnoreCase(name);
    }

    /**
     * Validate student data integrity
     */
    public boolean validateStudentIntegrity(Student student) {
        // Check if student has corresponding user
        if (student.getCreatedBy() == null) {
            return false;
        }

        // Check if student is in valid branch
        if (student.getBranch() == null) {
            return false;
        }

        // Add more validation rules as needed
        return true;
    }

    /**
     * Generate student report data
     */
    public Map<String, Object> generateStudentReport(Long branchId) {
        Map<String, Object> report = new HashMap<>();

        // Get basic stats
        Map<String, Object> stats = getStudentStatsByBranch(branchId);
        report.put("statistics", stats);

        // Get students by status
        List<Student> activeStudents = studentRepository.findByBranchBranchIdAndIsActiveTrue(branchId);
        List<Student> approvedStudents = studentRepository.findByBranchBranchIdAndIsApprovedTrue(branchId);

        report.put("activeStudentsCount", activeStudents.size());
        report.put("approvedStudentsCount", approvedStudents.size());

        return report;
    }

    /**
     * Check if username follows the expected pattern
     */
    public boolean isValidStudentUsername(String username) {
        // Expected pattern: YYTSBranchIDSerial (e.g., 25TS101)
        if (username == null || username.length() < 6) {
            return false;
        }

        // Check if it starts with year and has TS pattern
        String pattern = "^\\d{2}TS\\d{3}$";
        return username.matches(pattern);
    }

    /**
     * Extract branch ID from student username
     */
    public Long extractBranchIdFromUsername(String username) {
        if (!isValidStudentUsername(username)) {
            return null;
        }

        try {
            // Extract branch ID from position 4 (after YYTS)
            String branchStr = username.substring(4, 5);
            return Long.parseLong(branchStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generate next student ID preview for a branch
     */
    public String previewNextStudentId(Long branchId) {
        // This would integrate with UserIdGeneratorUtils
        // For now, return a placeholder
        String currentYear = String.valueOf(java.time.LocalDate.now().getYear()).substring(2);
        return currentYear + "TS" + branchId + "XX"; // XX will be replaced with actual serial
    }

    /**
     * Validate branch and check requestor access
     */
    public Branch validateAndGetBranch(Long branchId, User requestor) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + branchId));

        // Additional validation: if requestor is Admin, check if they have access to this branch
        String requestorRole = requestor.getUserRole().getRole().getRoleName();
        if ("Admin".equals(requestorRole)) {
            Long requestorBranchId = requestor.getUserRole().getBranch() != null ?
                    requestor.getUserRole().getBranch().getBranchId() : null;
            if (requestorBranchId != null && !requestorBranchId.equals(branchId)) {
                throw new UnauthorizedException("Admin can only create Staff/students in their assigned branch");
            }
        }

        return branch;
    }

    /**
     * Validate requestor has permission to create students
     */
    public void validateRequestorPermissions(User requestor) {
        if (requestor.getUserRole() == null || requestor.getUserRole().getRole() == null) {
            throw new UnauthorizedException("User role not found");
        }

        String requestorRole = requestor.getUserRole().getRole().getRoleName();
        if (!"Super Admin".equals(requestorRole) && !"Admin".equals(requestorRole)) {
            throw new UnauthorizedException("Only Super Admins and Admins can create students");
        }
    }


    /**
     * Create User entity with generated username as password
     */
    public User createUserEntity(String username, Roles studentRole, Branch branch, User requestor) {
        // Check if username already exists (double-check for safety)
        if (userRepository.findByUsername(username).isPresent()) {
            throw new InvalidInputException("Generated username already exists: " + username);
        }

        // Create user with username as temporary password
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(username)); // Use username as temporary password
        newUser.setActive(true);

        // Save user first
        User savedUser = userRepository.save(newUser);
        logger.info("User created with username: {}", username);

        // Create user role assignment
        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(studentRole);
        userRole.setBranch(branch);
        userRoleRepository.save(userRole);

        logger.info("User role assigned: Student role to user {}", username);

        return savedUser;
    }

    public Standard getStandardFromId( Long standardId ){
        if (standardId == null || standardId == 0) {
            throw new InvalidInputException("Standard ID is empty or invalid: " + standardId);
        }
       return standardRepository.findById(standardId)
                .orElseThrow(() -> new ResourceNotFoundException("Standard not found with ID: " + standardId));
    }

    public Board getBoardFromId( Long boardId ){
        if (boardId == null || boardId == 0) {
            throw new InvalidInputException("Standard ID is empty or invalid: " + boardId);
        }
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Standard not found with ID: " + boardId));
    }


//    /**
//     * Check if contact number is already in use
//     */
//    private void validateContactNumber(String contactNumber) {
//        if (StringUtils.isNotBlank(contactNumber)) {
//            Optional<Staff> existingStaff = staffRepo.findByContactNumberAndIsActiveTrue(contactNumber);
//            if (existingStaff.isPresent()) {
//                throw new IllegalArgumentException("Contact number already exists: " + contactNumber);
//            }
//        }
//    }

//    /**
//     * Validate manually provided credentials
//     */
//    private void validateProvidedCredentials(String username, String password) {
//        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
//            throw new IllegalArgumentException("Username and password are required when auto-generation is disabled");
//        }
//
//        // Username validation
//        if (username.length() < 3 || username.length() > 50) {
//            throw new IllegalArgumentException("Username must be between 3 and 50 characters");
//        }
//
//        // Password validation (reuse from utility service)
//        if (!userUtilityService.isPasswordValid(password)) {
//            throw new IllegalArgumentException("Password does not meet security requirements");
//        }
//    }
//
//    /**
//     * Get staff with full details including user account
//     */
//    public StaffWithUserResponseDTO getStaffWithUserDetails(Long staffId) {
//        Optional<Staff> staffOpt = staffRepo.findByIdWithUserDetails(staffId);
//        if (staffOpt.isEmpty()) {
//            throw new EntityNotFoundException("Staff not found with ID: " + staffId);
//        }
//
//        Staff staff = staffOpt.get();
//        return buildStaffWithUserResponse(staff, staff.getUser(), null); // Don't include password in get operations
//    }
//
//    /**
//     * Update staff admin status (only by Super Admin)
//     */
//    @Transactional
//    public StaffWithUserResponseDTO updateStaffAdminStatus(Long staffId, boolean isAdmin) {
//        // Validate permissions
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
//        String currentRole = getCurrentUserRole(currentUser);
//
//        if (!"Super Admin".equals(currentRole)) {
//            throw new AccessDeniedException("Only Super Admin can modify admin status");
//        }
//
//        // Get staff
//        Optional<Staff> staffOpt = staffRepo.findByIdWithUserDetails(staffId);
//        if (staffOpt.isEmpty()) {
//            throw new EntityNotFoundException("Staff not found with ID: " + staffId);
//        }
//
//        Staff staff = staffOpt.get();
//
//        // Update admin status
//        staff.setIsAdmin(isAdmin);
//
//        // Update user role if user account exists
//        if (staff.getUser() != null) {
//            updateUserRoleForAdminStatus(staff.getUser(), isAdmin, staff.getBranch());
//        }
//
//        Staff updatedStaff = staffRepo.save(staff);
//        return buildStaffWithUserResponse(updatedStaff, updatedStaff.getUser(), null);
//    }
//
//    /**
//     * Update user role based on admin status change
//     */
//    private void updateUserRoleForAdminStatus(User user, boolean isAdmin, Branch branch) {
//        String newRoleName = isAdmin ? "Admin" : "Faculty";
//        Roles newRole = rolesService.findByRoleName(newRoleName);
//
//        if (newRole == null) {
//            throw new EntityNotFoundException("Role not found: " + newRoleName);
//        }
//
//        UserRole userRole = user.getUserRole();
//        if (userRole != null) {
//            userRole.setRole(newRole);
//            userService.saveUserRole(userRole);
//        }
}
