package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole findByUser(User user);
}
