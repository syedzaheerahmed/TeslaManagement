package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Roles;
import java.util.List;

public interface RolesService {
    public String createRoles(Roles roles);
    public String updateRoles(Roles roles);
    public String deleteRoles(Long user_id);
    public Roles getRoleDetails(Long user_id);
    public List<Roles> getAllRoles();
}
