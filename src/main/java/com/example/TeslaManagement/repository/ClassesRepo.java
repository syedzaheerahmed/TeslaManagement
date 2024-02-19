package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ClassesRepo extends JpaRepository<Classes,Long> {
}
