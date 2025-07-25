package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.DTO.StudentRequestDTO;

import java.util.List;

public interface StudentService {
    StudentDTO createStudent(StudentRequestDTO studentRequestDTO);
    List<StudentDTO> getAllStudents();
    StudentDTO getStudentById(Long studentId);
    StudentDTO updateStudent(Long studentId, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long studentId);
    List<StudentDTO> getStudentByBranchId(Long branchId);
}