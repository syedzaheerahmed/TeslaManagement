package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.HQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HQRepo extends JpaRepository<HQ,Long> {
}
