package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Student;
import com.example.TeslaManagement.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentRepo repo;

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student) {
        repo.save(student);
    }
}
