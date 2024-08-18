package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.UserDetails;

import java.util.List;

public interface UserDetailsService {
    UserDetails createUser(UserDetails userDetail);
    UserDetails updateUser(Long id, UserDetails userDetail);
    void deleteUser(Long id);
    UserDetails getUserById(Long id);
    List<UserDetails> getAllUsers();
    public boolean checkUserAndPassword(String username,String password);
}
