package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s JOIN FETCH s.branch b WHERE b.branchId = :branchId")
    List<Student> findByBranch(@Param("branchId") Long branchId);
}
