package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.service.RolesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RolesController {

    RolesService rolesService;
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    //Get the given Role
    @GetMapping("/getRoleDetails/{user_id}")
    public Roles getRoleDetails(@PathVariable(value = "user_id") Long user_id) {
        return rolesService.getRoleDetails(user_id);
    }

    //Get all roles
    @GetMapping("/getAllRoleDetails")
    public List<Roles> getAllRolesDetails() {
        return rolesService.getAllRoles();
    }

    //Add role
    @PostMapping("/addRoleDetails")
    public String addRoleDetails(@RequestBody Roles roles) {
        return rolesService.createRoles(roles);
    }

    //update role
    @PutMapping("/updateRoleDetails")
    public String updateRoleDetails(@RequestBody Roles roles) {
        return rolesService.updateRoles(roles);
    }

    //delete role
    @DeleteMapping("/deleteRole/{user_id}")
    public String deleteRoleDetails(@PathVariable(value = "user_id") Long user_id) {
        return rolesService.deleteRoles(user_id);
    }
}
