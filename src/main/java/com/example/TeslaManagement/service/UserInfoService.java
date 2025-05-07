package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.User;

import java.util.List;

public interface UserInfoService {
    User createUser(User userDetail);
    User updateUser(Long id, User userDetail);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
    public boolean checkUserAndPassword(String username,String password);
}
