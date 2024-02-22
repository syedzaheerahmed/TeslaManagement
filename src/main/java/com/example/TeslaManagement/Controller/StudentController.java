package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Student;
import com.example.TeslaManagement.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Get the given Student details
    @GetMapping("/getStudentDetails/{student_attendance_id}")
    public Student getStudentDetails(@PathVariable(value = "student_attendance_id") Long student_id) {
        return studentService.getStudentDetails(student_id);
    }

    //Get all Student details
    @GetMapping("/getAllStudentDetails")
    public List<Student> getAllStudentDetails() {
        return studentService.getAllStudents();
    }

    //Add Student
    @PostMapping("/addStudentDetails")
    public String addStudentDetails(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    //update Student
    @PutMapping("/updateStudentDetails")
    public String updateStudentDetails(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    //delete Student
    @DeleteMapping("/deleteStudent/{student_id}")
    public String deleteStudentDetails(@PathVariable(value = "student_attendance_id") Long student_attendance_id) {
        return studentService.deleteStudent(student_attendance_id);
    }
}
