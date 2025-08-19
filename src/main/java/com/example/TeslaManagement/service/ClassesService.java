package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.ClassesDTO;
import com.example.TeslaManagement.model.Student;

import java.util.List;

public interface ClassesService {
    ClassesDTO createClass(ClassesDTO classesDTO);
    ClassesDTO updateClass(Long classId, ClassesDTO classesDTO);
    ClassesDTO getClassById(Long classId);
    void deleteClass(Long classId);
    List<ClassesDTO> getClassesByBranch(Long branchId);
    List<ClassesDTO> getClassesByStaff(Long staffId);
    List<ClassesDTO> getClassesByBranchAndStaff(Long branchId, Long staffId);
    List<ClassesDTO> getActiveClassesByBranch(Long branchId);
    void enrollStudent(Long classId, Long studentId);
    boolean canStaffAccessClass(Long staffId, Long classId);
    void deEnrollStudent(Long classId, Long studentId);
    List<Student> getActiveEnrolledStudents(Long classId);
}
