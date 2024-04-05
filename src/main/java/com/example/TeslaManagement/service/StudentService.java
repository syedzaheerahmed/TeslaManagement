package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Student;
import java.util.List;

public interface StudentService {
    String createStudent(Student student);
    String updateStudent(Student student);
    String deleteStudent(Long student_id);
    Student getStudentDetails(Long student_id);
    List<Student> getAllStudents();
}
