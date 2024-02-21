package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Student;
import java.util.List;

public interface StudentService {
    public String createStudent(Student student);
    public String updateStudent(Student student);
    public String deleteStudent(Long student_id);
    public Student getStudentDetails(Long student_id);
    public List<Student> getAllStudents();
}
