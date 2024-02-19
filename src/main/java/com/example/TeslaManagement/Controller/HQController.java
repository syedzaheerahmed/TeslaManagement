package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.HQ;
import com.example.TeslaManagement.repository.HQRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HQController {
    @Autowired
    HQRepo repo;

    @PostMapping("/addHQDetails")
    public void addHQDetails(@RequestBody HQ hq) {
        repo.save(hq);
    }
}
