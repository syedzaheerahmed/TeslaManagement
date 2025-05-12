package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByUsernameAndPassword(String username,String password);
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM User u " +
            "JOIN FETCH u.userRole ur " +
            "JOIN FETCH ur.role r " +
            "WHERE u.username = :username")
    User findByUsernameWithRole(@Param("username") String username);
}
