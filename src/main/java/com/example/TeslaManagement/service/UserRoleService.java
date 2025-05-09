package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.UserRole;
import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    UserRole getUserRoleById(Long id);
    UserRole createUserRole(UserRole userRole);
    UserRole updateUserRole(Long id, UserRole userRole);
    void deleteUserRole(Long id);
}