package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StaffRepo extends JpaRepository<Staff,Long> {

}
