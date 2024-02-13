package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentRepo extends JpaRepository <Student,Long> {

}
