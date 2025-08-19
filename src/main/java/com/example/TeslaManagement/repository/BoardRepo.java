package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BoardRepo extends JpaRepository<Board, Long> {
}
