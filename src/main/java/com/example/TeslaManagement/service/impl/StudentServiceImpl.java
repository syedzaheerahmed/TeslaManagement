package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Student;
import com.example.TeslaManagement.repository.StudentRepo;
import com.example.TeslaManagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;
    @Override
    public String createStudent(Student student) {
        try{
            studentRepo.save(student);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding student details";
        }
        return "Student details added successfully";
    }

    @Override
    public String updateStudent(Student student) {
        try{
            studentRepo.save(student);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating student details";
        }
        return "Student details updated successfully";
    }

    @Override
    public String deleteStudent(Long student_id) {
        try{
            studentRepo.deleteById(student_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting student details";
        }
        return "Student details deleted successfully";
    }

    @Override
    public Student getStudentDetails(Long student_id) {
        try {
            if(studentRepo.findById(student_id).isPresent()) {
                return studentRepo.getReferenceById(student_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}
