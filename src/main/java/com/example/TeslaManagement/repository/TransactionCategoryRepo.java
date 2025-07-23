package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionCategoryRepo extends JpaRepository<TransactionCategory,Long> {
}