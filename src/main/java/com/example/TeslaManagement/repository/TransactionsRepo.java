package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
    List<Transactions> findByTransactionDateGreaterThanEqualAndIsInvalidTransactionFalse(Date fromDate);

    @Query("SELECT t FROM Transactions t WHERE t.transactionDate >= :fromDate ORDER BY t.transactionDate DESC")
    List<Transactions> findTransactionsFromDate(
            @Param("fromDate") Date fromDate
    );
}
