package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.service.RolesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

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

    @PostMapping("/loginUser")
    public ResponseEntity<Boolean> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        boolean credentialsValid = rolesService.checkUserAndPassword(username, password);
        return ResponseEntity.ok(credentialsValid);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        boolean credentialsValid = rolesService.resetPassword(username, password);
        return ResponseEntity.ok(credentialsValid);
    }

    //Get all roles
    @GetMapping("/getAllRoleDetails")
    public List<Roles> getAllRolesDetails() {
        return rolesService.getAllRoles();
    }

    //Add role
    @PostMapping("/addRoleDetails")
    public Roles addRoleDetails(@RequestBody Roles roles) {
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
