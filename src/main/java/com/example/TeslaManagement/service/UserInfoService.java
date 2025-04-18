package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo createUser(UserInfo userDetail);
    UserInfo updateUser(Long id, UserInfo userDetail);
    void deleteUser(Long id);
    UserInfo getUserById(Long id);
    List<UserInfo> getAllUsers();
    public boolean checkUserAndPassword(String username,String password);
    //UserInfo loadUserByUsername(String username);
}
