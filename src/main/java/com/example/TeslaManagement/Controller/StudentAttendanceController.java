package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.StudentAttendance;
import com.example.TeslaManagement.service.StudentAttendanceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
public class StudentAttendanceController {
    StudentAttendanceService studentAttendanceService;
    public StudentAttendanceController(StudentAttendanceService studentAttendanceService) {
        this.studentAttendanceService = studentAttendanceService;
    }

    //Get the given attendance details
    @GetMapping("/getAttendanceDetails/{student_attendance_id}")
    public StudentAttendance getAttendanceDetails(@PathVariable(value = "student_attendance_id") Long student_attendance_id) {
        return studentAttendanceService.getStudentAttendanceDetails(student_attendance_id);
    }

    //Get all the student's attendance details
    @GetMapping("/getAllAttendanceDetails")
    public List<StudentAttendance> getAllAttendanceDetails() {
        return studentAttendanceService.getAllStudentAttendance();
    }

    //Add a student's attendance details
    @PostMapping("/addAttendanceDetails")
    public String addAttendanceDetails(@RequestBody StudentAttendance studentAttendance) {
        return studentAttendanceService.createStudentAttendance(studentAttendance);
    }

    //update a student's attendance details
    @PutMapping("/updateAttendanceDetails")
    public String updateAttendanceDetails(@RequestBody StudentAttendance studentAttendance) {
        return studentAttendanceService.updateStudentAttendance(studentAttendance);
    }

    //delete a student's attendance details
    @DeleteMapping("/deleteAttendance/{student_attendance_id}")
    public String deleteAttendanceDetails(@PathVariable(value = "student_attendance_id") Long student_attendance_id) {
        return studentAttendanceService.deleteStudentAttendance(student_attendance_id);
    }
}
