package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.Utils.PasswordGenerator;
import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.repository.RolesRepo;
import com.example.TeslaManagement.service.RolesService;
import org.springframework.stereotype.Service;
import java.time.Year;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    RolesRepo rolesRepo;
    public RolesServiceImpl(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }

    @Override
    public Roles createRolesForStaff(Long staff_id, String role, Integer[] branch_id) {
        Roles roles = new Roles();
        roles.setPassword(generatePassword());
        roles.setUsername(generateUserName(role, staff_id));
        roles.setStaff_id(staff_id);
        roles.setBranch_id(branch_id);
        roles.setRole(role);
        return createRoles(roles);
    }

    @Override
    public boolean checkUserAndPassword(String username, String password) {
        return rolesRepo.existsByUsernameAndPassword(username,password);
    }

    @Override
    public boolean resetPassword(String username, String password) {
        Roles user = rolesRepo.findByUsername(username);
        if (user != null) {
            user.setPassword(password);
            rolesRepo.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Roles createRoles(Roles roles) {
        try{
            return rolesRepo.save(roles);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }
    @Override
    public String updateRoles(Roles roles) {
        try{
            rolesRepo.save(roles);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating role details";
        }
        return "Role details updated successfully";
    }
    @Override
    public String deleteRoles(Long user_id) {
        try{
            rolesRepo.deleteById(user_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting role details";
        }
        return "Role details deleted successfully";
    }
    @Override
    public Roles getRoleDetails(Long user_id) {
        try {
            if(rolesRepo.findById(user_id).isPresent()) {
                return rolesRepo.getReferenceById(user_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }
    @Override
    public List<Roles> getAllRoles() {
        return rolesRepo.findAll();
    }

    public String generateUserName(String role,Long staff_id) {
        int year = Year.now().getValue();
        String username = "";
        if(role.equals("admin")) {
            username += "ST" + Integer.toString(year) + Long.toString(staff_id);
        }else if(role.equals("superadmin")) {
            username += "SA" + Integer.toString(year) + Long.toString(staff_id);
        }
        return username;
    }

    public String generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generateSecurePassword();
    }
}
