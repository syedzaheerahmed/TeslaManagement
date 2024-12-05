package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.TransactionAccountRequestDTO;
import com.example.TeslaManagement.model.Transactions;

import java.util.List;

public interface TransactionsService {

    Transactions createTransaction(Transactions transaction);

    Transactions getTransactionById(Long id);

    List<Transactions> getAllTransactions();

    Transactions updateTransaction(Long id, Transactions transaction);

    void deleteTransaction(Long id);

    Transactions createTransactionWithAccount( TransactionAccountRequestDTO transactions);
}
