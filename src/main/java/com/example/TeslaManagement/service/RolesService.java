package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Roles;
import java.util.List;

public interface RolesService {
    List<Roles> getAllRoles();
    Roles getRoleById(Long id);
    Roles createRole(Roles role);
    Roles updateRole(Long id, Roles role);
    void deleteRole(Long id);
}
