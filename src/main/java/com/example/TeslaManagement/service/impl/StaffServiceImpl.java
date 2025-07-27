package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.*;
import com.example.TeslaManagement.Utils.StudentUtilityService;
import com.example.TeslaManagement.Utils.UserIdGeneratorUtils;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private UserIdGeneratorUtils userIdGenerator;

    @Autowired
    private StudentUtilityService studentUtilityService;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RolesRepo rolesRepository;


    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public StaffDTO createStaff(StaffRequestDTO staffRequestDTO) {
        // Validate User
        User user = userRepo.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + 1));

        // Validate Faculty role (roleId = 4)
        UserRole userRole = user.getUserRole();
        if (userRole == null || userRole.getRole().getRoleId() != 4L) {
            throw new InvalidInputException("User must have the 'Faculty' role (roleId = 4).");
        }

        // Validate Branch
        Branch branch = branchRepo.findById(staffRequestDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + staffRequestDTO.getBranchId()));

        // Create Staff entity
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setBranch(branch);
        staff.setStaffName(staffRequestDTO.getStaffName());
        staff.setAddress(staffRequestDTO.getAddress());
        staff.setContactNumber(staffRequestDTO.getContactNumber());
        staff.setTeachingStaff(staffRequestDTO.isTeachingStaff());
        staff.setActive(staffRequestDTO.isActive());
        staff.setSalaryPaid(staffRequestDTO.isSalaryPaid());
        staff.setReasonForDeactivation(staffRequestDTO.getReasonForDeactivation());

        // Save and return DTO
        Staff savedStaff = staffRepo.save(staff);
        return convertToDTO(savedStaff);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffList = staffRepo.findAllWithDetails();
        return staffList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public StaffDTO getStaffById(Long id) {
        Staff staff = staffRepo.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return convertToDTO(staff);
    }

    @Override
    public List<StaffDTO> getStaffByBranchId(Long branchId){
        boolean branchExists = branchRepo.existsById(branchId);
        if(!branchExists) throw new EntityNotFoundException( "Branch Id doesn't exist "+branchId);
        return staffRepo.findByBranchBranchIdAndIsActiveTrue(branchId).stream()
                .map( this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public StaffDTO updateStaff(Long id, StaffRequestDTO staffRequestDTO) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        // Validate Branch
        Branch branch = branchRepo.findById(staffRequestDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + staffRequestDTO.getBranchId()));

        // Update Staff entity
        staff.setBranch(branch);
        staff.setStaffName(staffRequestDTO.getStaffName());
        staff.setAddress(staffRequestDTO.getAddress());
        staff.setContactNumber(staffRequestDTO.getContactNumber());
        staff.setTeachingStaff(staffRequestDTO.isTeachingStaff());
        staff.setActive(staffRequestDTO.isActive());
        staff.setSalaryPaid(staffRequestDTO.isSalaryPaid());
        staff.setReasonForDeactivation(staffRequestDTO.getReasonForDeactivation());

        // Save and return DTO
        Staff updatedStaff = staffRepo.save(staff);
        return convertToDTO(updatedStaff);
    }

    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public void deleteStaff(Long id) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setActive(false);
        staffRepo.save(staff);
    }

    /***
     * TODO: rewrite the roleId selection process make it robust and dynamic
     * */
    @Override
    @Transactional
    public StaffWithUserResponseDTO createStaffWithUser(StaffRequestDTO staffRequestDTO, User requestor) {
        logger.info("Starting student creation process with integrated user creation");

        Long staffRoleId = staffRequestDTO.getIsAdmin()? 3L : 4L;

        try {
            // 1. Validate requestor permissions
            studentUtilityService.validateRequestorPermissions(requestor);

            // 2. Validate branch exists and requestor has access
            Branch branch = studentUtilityService.validateAndGetBranch(staffRequestDTO.getBranchId(), requestor);

            // 3. Validate staff role exists
            Roles staffRole = rolesRepository.findById(staffRoleId)
                    .orElseThrow(() -> new ResourceNotFoundException("Staff role not found with ID: " + staffRoleId));

            // 4. Generate dynamic user ID
            String generatedUsername = generateStaffUsername(staffRequestDTO, branch, staffRoleId);

            // 5. Create User entity
            User newUser = studentUtilityService.createUserEntity(generatedUsername, staffRole, branch, requestor);

            // 6. Create Staff entity
            Staff newStaff = createStaffEntity(staffRequestDTO, branch, requestor);

            // 7. Return response with temporary password
            StaffWithUserResponseDTO response = convertStaffCreateToDTO(newStaff, generatedUsername, newUser);

            logger.info("Staff/Admin created successfully with username: {} and ID: {}",
                    generatedUsername, newStaff.getStaffId());

            return response;

        } catch (Exception e) {
            logger.error("Error in createStudentWithUser: {}", e.getMessage(), e);
            throw e; // Re-throw to be handled by controller
        }
    }

    /**
     * Generate username using custom logic or UserIdGenerator
     */
    private String generateStaffUsername(StaffRequestDTO request, Branch branch, Long roleId) {
        if (StringUtils.hasText(request.getCustomUsername())) {
            // Validate custom username is not already taken
            if (userRepository.findByUsername(request.getCustomUsername().trim()).isPresent()) {
                throw new InvalidInputException("Username '" + request.getCustomUsername() + "' is already taken");
            }
            return request.getCustomUsername().trim();
        } else {
            // Generate dynamic username using utility
            return userIdGenerator.generateUserId(roleId, branch.getBranchId());
        }
    }

    private Staff createStaffEntity(StaffRequestDTO request, Branch branch, User user) {
        Staff staff = new Staff();
        staff.setStaffName(request.getStaffName());
        staff.setAddress(request.getAddress());
        staff.setContactNumber(request.getContactNumber());
        staff.setTeachingStaff(request.isTeachingStaff());
        staff.setAdmin(Boolean.TRUE.equals(request.getIsAdmin()));
        staff.setCreatedBy(user);
        staff.setBranch(branch);
        staff.setActive(true);
        staff.setSalaryPaid(false);

        Staff savedStaff = staffRepo.save(staff);
        logger.info("Staff/Admin created with ID: {}", savedStaff.getStaffId());

        return savedStaff;
    }


    private StaffDTO convertToDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setAddress(staff.getAddress());
        dto.setContactNumber(staff.getContactNumber());
        dto.setActive(staff.isActive());
        dto.setTeachingStaff(staff.isTeachingStaff());
        dto.setReasonForDeactivation(staff.getReasonForDeactivation());
        dto.setBranchId(staff.getBranch().getBranchId());
        dto.setBranchName(staff.getBranch().getBranchName());
        dto.setUserId(staff.getUser().getUserId());
        dto.setUsername(staff.getUser().getUsername());
        dto.setSalaryPaid(staff.isSalaryPaid());
        dto.setCreatedAt(staff.getCreatedAt());
        dto.setUpdatedAt(staff.getUpdatedAt());
        return dto;
    }

    private StaffWithUserResponseDTO convertStaffCreateToDTO(Staff staff, String generatedUsername, User newUser) {
        StaffWithUserResponseDTO dto = new StaffWithUserResponseDTO();

        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setAddress(staff.getAddress());
        dto.setContactNumber(staff.getContactNumber());
        dto.setActive(staff.isActive());
        dto.setTeachingStaff(staff.isTeachingStaff());
        dto.setAdmin(staff.isAdmin());
        dto.setSalaryPaid(staff.isSalaryPaid());
        dto.setReasonForDeactivation(staff.getReasonForDeactivation());
        dto.setBranchId(staff.getBranch().getBranchId());
        dto.setBranchName(staff.getBranch().getBranchName());

        dto.setUserId(newUser.getUserId());
        dto.setUsername(newUser.getUsername());
        dto.setTemporaryPassword(generatedUsername);
        dto.setUserActive(newUser.isActive());
        dto.setUserCreatedAt(newUser.getCreatedAt());

        dto.setCreatedByUsername(staff.getCreatedBy().getUsername());

        dto.setCreatedAt(staff.getCreatedAt());
        dto.setUpdatedAt(staff.getUpdatedAt());
        return dto;
    }
}