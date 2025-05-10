package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.DTO.AdminCreateUserRequestDTO;
import com.example.TeslaManagement.Utils.PasswordGenerator;
import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.model.UserRole;
import com.example.TeslaManagement.repository.RolesRepo;
import com.example.TeslaManagement.repository.UserRoleRepo;
import com.example.TeslaManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.TeslaManagement.repository.UserRepo;

import java.time.Year;
import java.util.List;
import java.time.LocalDateTime;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    private RolesRepo rolesRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(User userDetail) { // This is for self-signup
        userDetail.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        userDetail.setActive(true);
        User savedUser = userRepo.save(userDetail);

        // Assign a default role, e.g., "USER"
        // Make sure you have a Role with roleName="USER" in your database
        Roles defaultRole = rolesRepo.findByRoleName("USER")
                .orElseThrow(() -> new RuntimeException("Error: Default Role USER not found."));

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(defaultRole);
        // userRole.setBranch(null); // If USER role is not branch-specific
        userRoleRepo.save(userRole);

        // Don't attempt to directly set userRole - the relationship is managed by JPA
        // Instead, if you need the user with the userRole immediately, fetch it again
        return userRepo.findById(savedUser.getUserId()).orElse(savedUser);
    }

    public User createUserByAdmin(AdminCreateUserRequestDTO request) {
        User newUser = new User();
        newUser.setUsername(request.username());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setActive(true);
        User savedUser = userRepo.save(newUser);

        Roles roleToAssign = rolesRepo.findByRoleName(request.roleName())
                .orElseThrow(() -> new RuntimeException("Error: Role " + request.roleName() + " not found."));

        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(roleToAssign);

        // Set branch if provided
        if (request.branchId() != null) {
            Branch branch = new Branch();  // You'll need to fetch this from a repository
            branch.setBranchId(request.branchId());
            userRole.setBranch(branch);
        }

        userRoleRepo.save(userRole);

        // Don't attempt to directly set userRole - relationship managed by JPA
        return userRepo.findById(savedUser.getUserId()).orElse(savedUser);
    }

    @Override
    public User updateUser(Long id, User userDetail) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail " + id));
        existingUser.setUsername(userDetail.getUsername());
        if (userDetail.getPassword() != null && !userDetail.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        }
        existingUser.setModifiedAt(LocalDateTime.now());
        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
        userRepo.delete(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public boolean resetPassword(String username, String password) {
        return false;
    }

    public String generatePassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        return passwordGenerator.generateSecurePassword();
    }


    public String generateUserName() {
        int year = Year.now().getValue();
        String username = "";

        return username;
    }


}