package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;
import com.example.TeslaManagement.DTO.StudentResponseDTO;
import com.example.TeslaManagement.model.User;

import java.util.List;

public interface StudentService {
    @Deprecated
    StudentDTO createStudent(StudentRequestDTO studentRequestDTO, User requestor);
    List<StudentDTO> getAllStudents();
    List<StudentDTO> getStudentById(Long studentId);
    StudentDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long studentId);
    List<StudentDTO> getStudentByBranchId(Long branchId);
    StudentResponseDTO createStudentWithUser(StudentRequestDTO studentRequestDTO, User requestor);
}