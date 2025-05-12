package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.CustomException.UnauthorizedException;
import com.example.TeslaManagement.DTO.AdminCreateUserRequestDTO;
import com.example.TeslaManagement.DTO.UserDTO;
import com.example.TeslaManagement.Utils.PasswordGenerator;
import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.model.UserRole;
import com.example.TeslaManagement.repository.BranchRepo;
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
import java.util.stream.Collectors;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserRoleRepo userRoleRepo;

    @Autowired
    private BranchRepo branchRepo;


    @Autowired
    private RolesRepo rolesRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User createUser(AdminCreateUserRequestDTO request) { // For self-signup

        // Validate the role being assigned
        Roles roleToAssign = rolesRepo.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + request.getRoleId()));
        String roleNameToAssign = roleToAssign.getRoleName();

        // Create the new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setActive(true);
        User savedUser = userRepo.save(newUser);

        // Assign the role and branch
        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(roleToAssign);
        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + request.getBranchId()));
            userRole.setBranch(branch);
        }
        userRoleRepo.save(userRole);

        logger.info("User created successfully: username={}, role={}", savedUser.getUsername(), roleNameToAssign);
        return savedUser;
    }

    @Override
    public User adminCreateUser(AdminCreateUserRequestDTO request, User requestor) {
        // Check requestor's role
        String requestorRole = requestor.getUserRole().getRole().getRoleName();
        if (!"Super Admin".equals(requestorRole) && !"Admin".equals(requestorRole)) {
            throw new UnauthorizedException("Only Super Admins and Admins can create new users.");
        }

        // Validate the role being assigned
        Roles roleToAssign = rolesRepo.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + request.getRoleId()));
        String roleNameToAssign = roleToAssign.getRoleName();

        if ("Admin".equals(roleNameToAssign) && !"Super Admin".equals(requestorRole)) {
            throw new UnauthorizedException("Only Super Admins can create Admins.");
        }

        if (!"Faculty".equals(roleNameToAssign) && !"Admin".equals(roleNameToAssign)) {
            throw new InvalidInputException("Only Faculty or Admin users can be created through this endpoint.");
        }

        // Validate branch_id if required
        if ("Faculty".equals(roleNameToAssign) && request.getBranchId() == null) {
            throw new InvalidInputException("Branch ID is required for Faculty users.");
        }

        // Create the new user
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setActive(true);
        User savedUser = userRepo.save(newUser);

        // Assign the role and branch
        UserRole userRole = new UserRole();
        userRole.setUser(savedUser);
        userRole.setRole(roleToAssign);
        if (request.getBranchId() != null) {
            Branch branch = branchRepo.findById(request.getBranchId())
                    .orElseThrow(() -> new ResourceNotFoundException("Branch not found with ID: " + request.getBranchId()));
            userRole.setBranch(branch);
        }
        userRoleRepo.save(userRole);

        logger.info("User created successfully: username={}, role={}", savedUser.getUsername(), roleNameToAssign);
        return savedUser;
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
    public UserDTO getUserById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return new UserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
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