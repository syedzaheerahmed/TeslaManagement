package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserInfoRepo extends JpaRepository<User, Long> {
    boolean existsByUsernameAndPassword(String username,String password);
    public User findByUsername(String username);
}
