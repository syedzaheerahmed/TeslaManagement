package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface AccountsRepo extends JpaRepository<Accounts, Long> {
}
