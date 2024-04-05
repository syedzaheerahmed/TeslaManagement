package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Roles;
import java.util.List;

public interface RolesService {
    Roles createRoles(Roles roles);
    String updateRoles(Roles roles);
    String deleteRoles(Long user_id);
    Roles getRoleDetails(Long user_id);
    List<Roles> getAllRoles();
    Roles createRolesForStaff(Long staff_id, String role, Integer[] branch_id);
    boolean checkUserAndPassword(String username, String password);
    boolean resetPassword(String username, String password);
}
