package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;
import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.model.Student;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.model.UserRole;
import com.example.TeslaManagement.repository.BranchRepo;
import com.example.TeslaManagement.repository.StudentRepo;
import com.example.TeslaManagement.repository.UserRepo;
import com.example.TeslaManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private BranchRepo branchRepository;

    private static final Long STUDENT_ROLE_ID = 5L;

    @Transactional
    @Override
    public StudentDTO createStudent(StudentRequestDTO studentRequestDTO) {
        // Validate userId and role
        User user = userRepository.findById(studentRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + studentRequestDTO.getUserId()));

        UserRole userRole = user.getUserRole();
        if (userRole == null || !userRole.getRole().getRoleId().equals(STUDENT_ROLE_ID)) {
            throw new RuntimeException("User with ID: " + studentRequestDTO.getUserId() + " does not have the Student role");
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