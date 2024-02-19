package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.StudentAttendance;
import com.example.TeslaManagement.repository.StudentAttendanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAttendanceController {
    @Autowired
    StudentAttendanceRepo repo;

    @PostMapping("/addStudentAttendance")
    public void addStudentAttendance(@RequestBody StudentAttendance studentAttendance) {
        repo.save(studentAttendance);
    }
}
