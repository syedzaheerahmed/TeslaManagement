package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RolesRepo extends JpaRepository<Roles,Long> {

}