package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.UserDetails;
import com.example.TeslaManagement.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.example.TeslaManagement.repository.UserDetailsRepo;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDetailsRepo userDetailsRepo;

    @Override
    public UserDetails createUser(UserDetails userDetail) {
        return userDetailsRepo.save(userDetail);

    }

    @Override
    public UserDetails updateUser(Long id, UserDetails userDetail) {
        UserDetails existingUser = userDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
        existingUser.setUsername(userDetail.getUsername());
        existingUser.setPassword(userDetail.getPassword());
        existingUser.setRole(userDetail.getRole());
        existingUser.setBranch(userDetail.getBranch());
        existingUser.setModifiedAt(LocalDateTime.now());
        return userDetailsRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserDetails user = userDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
        userDetailsRepo.delete(user);
    }

    @Override
    public UserDetails getUserById(Long id) {
        return userDetailsRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
    }

    @Override
    public List<UserDetails> getAllUsers() {
        return userDetailsRepo.findAll();
    }

    @Override
    public boolean checkUserAndPassword(String username, String password) {
        return userDetailsRepo.existsByUsernameAndPassword(username, password);
    }

}