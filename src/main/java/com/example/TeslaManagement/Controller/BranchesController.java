package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Branches;
import com.example.TeslaManagement.repository.BranchesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchesController {
    @Autowired
    BranchesRepo repo;

    @PostMapping("/addBranchDetails")
    public void addBranchDetails(@RequestBody Branches branches) {
        repo.save(branches);
    }
}
