package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.UserInfo;
import com.example.TeslaManagement.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import com.example.TeslaManagement.repository.UserInfoRepo;

import java.util.HashSet;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Set;

@Service(value = "userService")
public class UserInfoServiceImpl implements UserInfoService, UserDetailsService {
    @Autowired
    UserInfoRepo userInfoRepo;

    @Override
    public UserInfo createUser(UserInfo userDetail) {
        return userInfoRepo.save(userDetail);

    }

    @Override
    public UserInfo updateUser(Long id, UserInfo userDetail) {
        UserInfo existingUser = userInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
        existingUser.setUsername(userDetail.getUsername());
        existingUser.setPassword(userDetail.getPassword());
        existingUser.setRole(userDetail.getRole());
        existingUser.setBranch(userDetail.getBranch());
        existingUser.setModifiedAt(LocalDateTime.now());
        return userInfoRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserInfo user = userInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
        userInfoRepo.delete(user);
    }

    @Override
    public UserInfo getUserById(Long id) {
        return userInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserDetail "+id));
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepo.findAll();
    }

    @Override
    public boolean checkUserAndPassword(String username, String password) {
        return userInfoRepo.existsByUsernameAndPassword(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //logger.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userInfoRepo.findByUsername(username);
        if(user == null){
            //logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }
        //logger.info("User Authenticated Successfully..!!!");
        //return new CustomUserDetails(user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserInfo user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

}