package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Roles;
import java.util.List;

public interface RolesService {
    public Roles createRoles(Roles roles);
    public String updateRoles(Roles roles);
    public String deleteRoles(Long user_id);
    public Roles getRoleDetails(Long user_id);
    public List<Roles> getAllRoles();
    public Roles createRolesForStaff(Long staff_id,String role,Integer[] branch_id);
    public boolean checkUserAndPassword(String username,String password);
    public boolean resetPassword(String username, String password);
}
