package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Roles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TeslaManagement")
public class TeslaManagementController {
    @GetMapping("/login")
    public String login()
    {
        return "login module";
    }

}
