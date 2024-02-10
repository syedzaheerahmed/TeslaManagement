package com.example.TeslaManagement.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeslaManagementController {
    @GetMapping("/")
    public String getHome(){
        return "Home";
    }
}
