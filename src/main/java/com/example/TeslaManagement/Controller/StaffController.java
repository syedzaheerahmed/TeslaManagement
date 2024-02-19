package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Staff;
import com.example.TeslaManagement.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {

    @Autowired
    StaffRepo repo;

    @PostMapping("/addStaffDetails")
    public void addStaffDetails(@RequestBody Staff staff) {
        repo.save(staff);
    }
}
