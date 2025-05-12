package com.example.TeslaManagement.repository;


import com.example.TeslaManagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StaffRepo extends JpaRepository<Staff, Long> {
    @Query("SELECT s FROM Staff s JOIN FETCH s.branch JOIN FETCH s.user WHERE s.staffId = :id")
    Optional<Staff> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT s FROM Staff s JOIN FETCH s.branch JOIN FETCH s.user")
    List<Staff> findAllWithDetails();
}
