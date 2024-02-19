package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Classes;
import com.example.TeslaManagement.repository.ClassesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassesController {
    @Autowired
    ClassesRepo repo;

    @PostMapping("/addClassDetails")
    public void addClassDetails(@RequestBody Classes classes) {
        repo.save(classes);
    }
}
