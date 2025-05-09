package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.repository.RolesRepo;
import com.example.TeslaManagement.service.RolesService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepo rolesRepo;

    @Override
    public List<Roles> getAllRoles() {
        return rolesRepo.findAll();
    }

    @Override
    public Roles getRoleById(Long id) {
        return rolesRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
    }

    @Override
    public Roles createRole(Roles role) {
        return rolesRepo.save(role);
    }

    @Override
    public Roles updateRole(Long id, Roles roleDetails) {
        Roles role = getRoleById(id);
        role.setRoleName(roleDetails.getRoleName());
        return rolesRepo.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        rolesRepo.deleteById(id);
    }
}
