package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
}
