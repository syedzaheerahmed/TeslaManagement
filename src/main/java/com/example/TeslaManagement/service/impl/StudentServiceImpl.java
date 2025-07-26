package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.CustomException.UnauthorizedException;
import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;
import com.example.TeslaManagement.DTO.StudentResponseDTO;
import com.example.TeslaManagement.Utils.UserIdGeneratorUtils;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BranchRepo branchRepository;

    @Autowired
    private RolesRepo rolesRepository;

    @Autowired
    private UserRoleRepo userRoleRepository;

    @Autowired
    private UserIdGeneratorUtils userIdGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Long STUDENT_ROLE_ID = 5L;

    @Override
    @Transactional
    public StudentResponseDTO createStudentWithUser(StudentRequestDTO studentRequestDTO, User requestor) {
        logger.info("Starting student creation process with integrated user creation");

        try {
            // 1. Validate requestor permissions
            validateRequestorPermissions(requestor);

            // 2. Validate branch exists and requestor has access
            Branch branch = validateAndGetBranch(studentRequestDTO.getBranchId(), requestor);

            // 3. Validate student role exists
            Roles studentRole = rolesRepository.findById(STUDENT_ROLE_ID)
                    .orElseThrow(() -> new ResourceNotFoundException("Student role not found with ID: " + STUDENT_ROLE_ID));

            // 4. Generate dynamic user ID
            String generatedUsername = generateUsername(studentRequestDTO, branch);

            // 5. Create User entity
            User newUser = createUserEntity(generatedUsername, studentRole, branch, requestor);

            // 6. Create Student entity
            Student newStudent = createStudentEntity(studentRequestDTO, branch, newUser);

            // 7. Return response with temporary password
            StudentResponseDTO response = mapToResponseDTO(newStudent, generatedUsername);

            logger.info("Student created successfully with username: {} and ID: {}",
                    generatedUsername, newStudent.getStudentId());

            return response;

        } catch (Exception e) {
            logger.error("Error in createStudentWithUser: {}", e.getMessage(), e);
            throw e; // Re-throw to be handled by controller
        }
    }

    @Transactional
    @Override
    public StudentDTO createStudent(StudentRequestDTO studentRequestDTO, User requestor) {
        // Validate userId and role
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + 1L));

        UserRole userRole = user.getUserRole();
        if (userRole == null || !userRole.getRole().getRoleId().equals(STUDENT_ROLE_ID)) {
            throw new RuntimeException("User with ID: " + 1L + " does not have the Student role");
        }

        // Validate branch
        Branch branch = branchRepository.findById(studentRequestDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found with ID: " + studentRequestDTO.getBranchId()));

        // Create student entity
        Student student = new Student();
        student.setStudentName(studentRequestDTO.getStudentName());
        student.setDob(studentRequestDTO.getDob());
        student.setGender(studentRequestDTO.getGender());
        student.setStudentAddress(studentRequestDTO.getStudentAddress());
        student.setParentName(studentRequestDTO.getParentName());
        student.setParentContact(studentRequestDTO.getParentContact());
        student.setSchoolName(studentRequestDTO.getSchoolName());
        student.setSchoolStd(studentRequestDTO.getSchoolStd());
        student.setBoardOfSchool(studentRequestDTO.getBoardOfSchool());
        student.setBatchYear(studentRequestDTO.getBatchYear());
        student.setBranch(branch);
        student.setCreatedBy(user); // Assuming the user creating the student is the same as the student user
        student.setActive(true);
        student.setApproved(false);
        student.setFeesPaid(studentRequestDTO.isFeesPaid());

        // Save student
        Student savedStudent = studentRepository.save(student);
        return mapToDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        return mapToDTO(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentByBranchId(Long branchId) {
        boolean branchExists = branchRepository.existsById(branchId);
        if(!branchExists) throw new EntityNotFoundException( "Branch Id doesn't exist "+branchId);
        return studentRepository.findByBranch(branchId).stream()
                .map( this::mapToDTO).collect(Collectors.toList());

    }

    @Transactional
    @Override
    public StudentDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

        // Update fields
        student.setStudentName(studentRequestDTO.getStudentName());
        student.setDob(studentRequestDTO.getDob());
        student.setGender(studentRequestDTO.getGender());
        student.setStudentAddress(studentRequestDTO.getStudentAddress());
        student.setParentName(studentRequestDTO.getParentName());
        student.setParentContact(studentRequestDTO.getParentContact());
        student.setSchoolName(studentRequestDTO.getSchoolName());
        student.setSchoolStd(studentRequestDTO.getSchoolStd());
        student.setBoardOfSchool(studentRequestDTO.getBoardOfSchool());
        student.setBatchYear(studentRequestDTO.getBatchYear());
        student.setFeesPaid(studentRequestDTO.isFeesPaid());

        Branch branch = branchRepository.findById(studentRequestDTO.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found with ID: " + studentRequestDTO.getBranchId()));
        student.setBranch(branch);

        Student updatedStudent = studentRepository.save(student);
        return mapToDTO(updatedStudent);
    }

    @Transactional
    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));
        studentRepository.delete(student);
    }

    /**
     * Validate requestor has permission to create students
     */
    private void validateRequestorPermissions(User requestor) {
        if (requestor.getUserRole() == null || requestor.getUserRole().getRole() == null) {
            throw new UnauthorizedException("User role not found");
        }

        String requestorRole = requestor.getUserRole().getRole().getRoleName();
        if (!"Super Admin".equals(requestorRole) && !"Admin".equals(requestorRole)) {
            throw new UnauthorizedException("Only Super Admins and Admins can create students");
        }
    }

    /**
     * Validate branch and check requestor access
     */
    private Branch validateAndGetBranch(Long branchId, User requestor) {
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + branchId));

        // Additional validation: if requestor is Admin, check if they have access to this branch
        String requestorRole = requestor.getUserRole().getRole().getRoleName();
        if ("Admin".equals(requestorRole)) {
            Long requestorBranchId = requestor.getUserRole().getBranch() != null ?
                    requestor.getUserRole().getBranch().getBranchId() : null;
            if (requestorBranchId != null && !requestorBranchId.equals(branchId)) {
                throw new UnauthorizedException("Admin can only create students in their assigned branch");
            }
        }

        return branch;
    }

    /**
     * Generate username using custom logic or UserIdGenerator
     */
    private String generateUsername(StudentRequestDTO request, Branch branch) {
        if (StringUtils.hasText(request.getCustomUsername())) {
            // Validate custom username is not already taken
            if (userRepository.findByUsername(request.getCustomUsername().trim()).isPresent()) {
                throw new InvalidInputException("Username '" + request.getCustomUsername() + "' is already taken");
            }
            return request.getCustomUsername().trim();
        } else {
            // Generate dynamic username using utility
            return userIdGenerator.generateUserId(STUDENT_ROLE_ID, branch.getBranchId());
        }
    }

    /**
     * Create User entity with generated username as password
     */
    private User createUserEntity(String username, Roles studentRole, Branch branch, User requestor) {
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

    /**
     * Create Student entity
     */
    private Student createStudentEntity(StudentRequestDTO request, Branch branch, User user) {
        Student student = new Student();
        student.setStudentName(request.getStudentName());
        student.setDob(request.getDob());
        student.setGender(request.getGender());
        student.setStudentAddress(request.getStudentAddress());
        student.setParentName(request.getParentName());
        student.setParentContact(request.getParentContact());
        student.setSchoolName(request.getSchoolName());
        student.setSchoolStd(request.getSchoolStd());
        student.setBoardOfSchool(request.getBoardOfSchool());
        student.setBatchYear(request.getBatchYear());
        student.setBranch(branch);
        student.setCreatedBy(user); // Set the newly created user as createdBy
        student.setActive(true);
        student.setApproved(false); // Default to not approved
        student.setFeesPaid(request.isFeesPaid());

        Student savedStudent = studentRepository.save(student);
        logger.info("Student created with ID: {}", savedStudent.getStudentId());

        return savedStudent;
    }

    /**
     * Map entities to response DTO
     */
    private StudentResponseDTO mapToResponseDTO(Student student, String temporaryPassword) {
        StudentResponseDTO dto = new StudentResponseDTO();

        // Student details
        dto.setStudentId(student.getStudentId());
        dto.setStudentName(student.getStudentName());
        dto.setDob(student.getDob());
        dto.setGender(student.getGender());
        dto.setStudentAddress(student.getStudentAddress());
        dto.setParentName(student.getParentName());
        dto.setParentContact(student.getParentContact());
        dto.setSchoolName(student.getSchoolName());
        dto.setSchoolStd(student.getSchoolStd());
        dto.setBoardOfSchool(student.getBoardOfSchool());
        dto.setBatchYear(student.getBatchYear());
        dto.setActive(student.isActive());
        dto.setApproved(student.isApproved());
        dto.setFeesPaid(student.isFeesPaid());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());

        // User details
        dto.setUserId(student.getCreatedBy().getUserId());
        dto.setUsername(student.getCreatedBy().getUsername());
        dto.setTemporaryPassword(temporaryPassword); // Show temporary password only once
        dto.setUserActive(student.getCreatedBy().isActive());
        dto.setUserCreatedAt(student.getCreatedBy().getCreatedAt());

        // Branch details
        dto.setBranchId(student.getBranch().getBranchId());
        dto.setBranchName(student.getBranch().getBranchName());

        // Creator details
        dto.setCreatedByUsername(student.getCreatedBy().getUsername());

        return dto;
    }

    private StudentDTO mapToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setStudentName(student.getStudentName());
        dto.setDob(student.getDob());
        dto.setGender(student.getGender());
        dto.setStudentAddress(student.getStudentAddress());
        dto.setParentName(student.getParentName());
        dto.setParentContact(student.getParentContact());
        dto.setSchoolName(student.getSchoolName());
        dto.setSchoolStd(student.getSchoolStd());
        dto.setBoardOfSchool(student.getBoardOfSchool());
        dto.setBatchYear(student.getBatchYear());
        dto.setBranchId(student.getBranch().getBranchId());
        dto.setUserId(student.getCreatedBy().getUserId());
        dto.setActive(student.isActive());
        dto.setApproved(student.isApproved());
        dto.setFeesPaid(student.isFeesPaid());
        dto.setCreatedAt(student.getCreatedAt());
        dto.setUpdatedAt(student.getUpdatedAt());
        return dto;
    }
}