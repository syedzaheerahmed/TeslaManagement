package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    boolean existsByUsernameAndPassword(String username,String password);
    public UserInfo findByUsername(String username);
}
