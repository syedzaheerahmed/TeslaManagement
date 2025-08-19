package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.BusinessException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.ClassesDTO;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassesServiceImpl implements ClassesService {

    private static final Logger logger = LoggerFactory.getLogger(ClassesServiceImpl.class);
    private final ClassesRepo classesRepository;
    private final EnrollmentsRepo enrollmentsRepository;
    private final StudentRepo studentsRepository;
    private final StaffRepo staffRepository;
    private final BranchRepo branchRepository;

    // CRUD Operations

    public ClassesDTO createClass(ClassesDTO classesDTO) {
        logger.info("Creating new class: {}", classesDTO.getClassName());

        // Validate staff belongs to the branch
        validateStaffBranchAccess(classesDTO.getStaffId(), classesDTO.getBranchId());

        // Check for duplicate class name in the same branch
        if (classesRepository.existsByClassNameAndBranchBranchId(classesDTO.getClassName(), classesDTO.getBranchId())) {
            throw new BusinessException("ClassEntity with batch name '" + classesDTO.getClassName() +
                    "' already exists in this branch");
        }

        // Check for timing conflicts
        validateClassTimings(classesDTO.getBranchId(), classesDTO.getClassTimings(), null);
        Staff staff = staffRepository.findById(classesDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + classesDTO.getStaffId()));
        Branch branchById = branchRepository.findById(classesDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + classesDTO.getBranchId()));

        ClassEntity classes = mapToEntity(classesDTO, staff, branchById);
        classes.setCreatedAt(LocalDateTime.now());
        classes.setUpdatedAt(LocalDateTime.now());
        classes.setActive(true);

        ClassEntity savedClassEntity = classesRepository.save(classes);
        logger.info("ClassEntity created successfully with ID: {}", savedClassEntity.getClassId());

        return mapToDTO(savedClassEntity);
    }

    public ClassesDTO updateClass(Long classId, ClassesDTO classesDTO) {
        logger.info("Updating class with ID: {}", classId);

        ClassEntity existingClassEntity = classesRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("ClassEntity not found with ID: " + classId));

        // Validate staff belongs to the branch
        validateStaffBranchAccess(classesDTO.getStaffId(), classesDTO.getBranchId());

        // Check for duplicate batch name (excluding current class)
        if (!existingClassEntity.getClassName().equals(classesDTO.getClassName()) &&
                classesRepository.existsByClassNameAndBranchBranchId(classesDTO.getClassName(), classesDTO.getBranchId())) {
            throw new BusinessException("ClassEntity with batch name '" + classesDTO.getClassName() +
                    "' already exists in this branch");
        }

        // Check for timing conflicts (excluding current class)
        if (!existingClassEntity.getClassTimings().equals(classesDTO.getClassTimings())) {
            validateClassTimings(classesDTO.getBranchId(), classesDTO.getClassTimings(), classId);
        }
        Staff staff = staffRepository.findById(classesDTO.getStaffId())
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with ID: " + classesDTO.getStaffId()));
        Branch branchById = branchRepository.findById(classesDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + classesDTO.getBranchId()));

        updateEntityFromDTO(existingClassEntity, classesDTO, staff, branchById);
        existingClassEntity.setUpdatedAt(LocalDateTime.now());

        ClassEntity updatedClassEntity = classesRepository.save(existingClassEntity);
        logger.info("ClassEntity updated successfully with ID: {}", updatedClassEntity.getClassId());

        return mapToDTO(updatedClassEntity);
    }

    public ClassesDTO getClassById(Long classId) {
        ClassEntity classes = classesRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("ClassEntity not found with ID: " + classId));
        return mapToDTO(classes);
    }

    public void deleteClass(Long classId) {
        logger.info("Deleting class with ID: {}", classId);

        ClassEntity existingClassEntity = classesRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("ClassEntity not found with ID: " + classId));

        // Check if class has active enrollments
        long enrollmentCount = enrollmentsRepository.countByClassId(classId);
        if (enrollmentCount > 0) {
            // Soft delete - mark as inactive instead of hard delete
            existingClassEntity.setActive(false);
            existingClassEntity.setUpdatedAt(LocalDateTime.now());
            classesRepository.save(existingClassEntity);
            logger.info("ClassEntity marked as inactive due to existing enrollments: {}", classId);
        } else {
            classesRepository.delete(existingClassEntity);
            logger.info("ClassEntity deleted successfully: {}", classId);
        }
    }

    // Helper Methods

    public List<ClassesDTO> getClassesByBranch(Long branchId) {
        return classesRepository.findByBranchBranchId(branchId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ClassesDTO> getClassesByStaff(Long staffId) {
        return classesRepository.findByStaffStaffId(staffId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ClassesDTO> getClassesByBranchAndStaff(Long branchId, Long staffId) {
        // Validate staff belongs to branch
        validateStaffBranchAccess(staffId, branchId);

        return classesRepository.findByBranchBranchIdAndStaffStaffId(branchId, staffId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<ClassesDTO> getActiveClassesByBranch(Long branchId) {
        return classesRepository.findActiveClassesByBranch(branchId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

//    public List<ClassWithEnrollmentCountDTO> getClassesWithEnrollmentCount(Long branchId) {
//        List<Object[]> results = classesRepository.findClassesWithEnrollmentCount(branchId);
//
//        return results.stream()
//                .map(result -> {
//                    ClassEntity classes = (ClassEntity) result[0];
//                    Long enrollmentCount = (Long) result[1];
//
//                    ClassWithEnrollmentCountDTO dto = new ClassWithEnrollmentCountDTO();
//                    dto.setClassInfo(mapToDTO(classes));
//                    dto.setEnrollmentCount(enrollmentCount);
//                    return dto;
//                })
//                .collect(Collectors.toList());
//    }

//    public List<ClassesDTO> searchClassesByBatchName(String batchName) {
//        return classesRepository.findByBatchNameContainingIgnoreCase(batchName)
//                .stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    public List<ClassesDTO> searchClassesByBatchName(Long branchId, String batchName) {
//        return classesRepository.findByBranchIdAndBatchNameContainingIgnoreCase(branchId, batchName)
//                .stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }

    public boolean canStaffAccessClass(Long staffId, Long classId) {
        return classesRepository.findById(classId)
                .map(classes -> classes.getStaff().getStaffId().equals(staffId))
                .orElse(false);
    }

    // Enrollment Management

    @Transactional
    public void enrollStudent(Long classId, Long studentId) {
        logger.info("Enrolling student {} in class {}", studentId, classId);

        // Validate class exists
        ClassEntity classes = classesRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("ClassEntity not found with ID: " + classId));

        // Validate student exists and is active
        Student student = studentsRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));

        if (!student.isActive()) {
            throw new BusinessException("Cannot enroll inactive student");
        }

        if (!student.isFeesPaid()) {
            throw new BusinessException("Cannot enroll student with unpaid fees");
        }

        // Validate student and class are in same branch
        if (!student.getBranch().getBranchId().equals(classes.getBranch().getBranchId())) {
            throw new BusinessException("Student and class must belong to the same branch");
        }

        // Check if already enrolled
        if (enrollmentsRepository.existsByClassIdAndStudentId(classId, studentId)) {
            throw new BusinessException("Student is already enrolled in this class");
        }

        // Create enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setClassEntity(classes);
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(java.time.LocalDate.now());
        enrollment.setCreatedAt(LocalDateTime.now());
        enrollment.setUpdatedAt(LocalDateTime.now());

        enrollmentsRepository.save(enrollment);
        logger.info("Student {} successfully enrolled in class {}", studentId, classId);
    }

    @Transactional
    public void deEnrollStudent(Long classId, Long studentId) {
        logger.info("De-enrolling student {} from class {}", studentId, classId);

        Enrollment enrollment = enrollmentsRepository.findByClassIdAndStudentId(classId, studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found for student " + studentId + " in class " + classId));
        enrollment.setActive(false);

        enrollmentsRepository.save(enrollment);
        logger.info("Student {} successfully de-enrolled from class {}", studentId, classId);
    }

//    public List<Student> getEnrolledStudents(Long classId) {
//        List<Enrollment> enrollments = enrollmentsRepository.findByClassId(classId);
//
//        return enrollments.stream()
//                .map(enrollment -> studentsRepository.findById(enrollment.getStudentId()))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

    public List<Student> getActiveEnrolledStudents(Long classId) {
        List<Object[]> results = enrollmentsRepository.findEnrollmentsWithStudentDetails(classId);

        return results.stream()
                .map(result -> (Student) result[1])
                .collect(Collectors.toList());
    }

//    public List<ClassEntity> getStudentClasses(Long studentId) {
//        List<Enrollment> enrollments = enrollmentsRepository.findByStudentId(studentId);
//
//        return enrollments.stream()
//                .map(enrollment -> classesRepository.findById(enrollment.getClassId()))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }

    // Validation Methods

    private void validateStaffBranchAccess(Long staffId, Long branchId) {
        // This would typically validate against a Staff entity
        // For now, we'll assume validation is done at controller level
        // In a real implementation, you'd check:
        // Staff staff = staffRepository.findById(staffId)...
        // if (!staff.getBranchId().equals(branchId)) throw new BusinessException(...)
    }

    private void validateClassTimings(Long branchId, String timings, Long excludeClassId) {
        Long excludeId = excludeClassId != null ? excludeClassId : -1L;
        List<ClassEntity> overlappingClassEntities = classesRepository.findOverlappingClasses(branchId, timings, excludeId);

        if (!overlappingClassEntities.isEmpty()) {
            throw new BusinessException("ClassEntity timings conflict with existing class: " +
                    overlappingClassEntities.get(0).getClassTimings());
        }
    }

    // Mapping Methods

    private ClassEntity mapToEntity(ClassesDTO dto, Staff staff, Branch branch) {
        ClassEntity classes = new ClassEntity();
        classes.setClassName(dto.getClassName());
        classes.setClassTimings(dto.getClassTimings());
        classes.setBranch(branch);
        classes.setStaff(staff);
        return classes;
    }

    private void updateEntityFromDTO(ClassEntity entity, ClassesDTO dto, Staff staff, Branch branch) {
        entity.setClassName(dto.getClassName());
        entity.setClassTimings(dto.getClassTimings());
        entity.setBranch(branch);
        entity.setStaff(staff);
    }

    private ClassesDTO mapToDTO(ClassEntity entity) {
        return ClassesDTO.builder()
                .classId(entity.getClassId())
                .className(entity.getClassName())
                .classTimings(entity.getClassTimings())
                .branchId(entity.getBranch().getBranchId())
                .staffId(entity.getStaff().getStaffId())
                .isActive(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
