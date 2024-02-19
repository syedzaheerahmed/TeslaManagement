package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.repository.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolesController {

    @Autowired
    RolesRepo repo;

    @PostMapping("/addRole")
    public void addRole(@RequestBody Roles roles) {
        repo.save(roles);
    }
}
