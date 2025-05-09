package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.UserRole;
import com.example.TeslaManagement.repository.UserRoleRepo;
import com.example.TeslaManagement.service.UserRoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    public UserRole getUserRoleById(Long id) {
        return userRoleRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserRole not found with id: " + id));
    }

    @Override
    public UserRole createUserRole(UserRole userRole) {
        return userRoleRepo.save(userRole);
    }

    @Override
    public UserRole updateUserRole(Long id, UserRole updatedUserRole) {
        UserRole existing = getUserRoleById(id);
        existing.setBranch(updatedUserRole.getBranch());
        existing.setRole(updatedUserRole.getRole());
        existing.setUser(updatedUserRole.getUser());
        return userRoleRepo.save(existing);
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleRepo.deleteById(id);
    }
}