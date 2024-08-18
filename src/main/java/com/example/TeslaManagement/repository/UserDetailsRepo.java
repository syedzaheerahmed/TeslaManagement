package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {
    boolean existsByUsernameAndPassword(String username,String password);
}
