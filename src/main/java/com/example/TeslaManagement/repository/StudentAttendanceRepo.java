package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance,Long> {

}
