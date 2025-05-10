package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.AdminCreateUserRequestDTO;
import com.example.TeslaManagement.DTO.UserDTO;
import com.example.TeslaManagement.model.User;

import java.util.List;

public interface UserService {
    User createUser(User userDetail);
    User updateUser(Long id, User userDetail);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<UserDTO> getAllUsers();
    boolean resetPassword(String username, String password);
    User createUserByAdmin(AdminCreateUserRequestDTO request);
}
