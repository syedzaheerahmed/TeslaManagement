package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BranchesRepo extends JpaRepository<Branches,Long> {
}
