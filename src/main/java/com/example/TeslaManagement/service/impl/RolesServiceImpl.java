package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.repository.RolesRepo;
import com.example.TeslaManagement.service.RolesService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    RolesRepo rolesRepo;
    public RolesServiceImpl(RolesRepo rolesRepo) {
        this.rolesRepo = rolesRepo;
    }
    @Override
    public String createRoles(Roles roles) {
        try{
            rolesRepo.save(roles);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding role details";
        }
        return "Role details added successfully";
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
}
