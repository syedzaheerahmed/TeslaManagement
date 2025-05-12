package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface BranchRepo extends JpaRepository<Branch, Long> {
}
