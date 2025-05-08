package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.TransactionAccountRequestDTO;
import com.example.TeslaManagement.model.Transaction;

import java.util.List;

public interface TransactionsService {

    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    Transaction updateTransaction(Long id, Transaction transaction);

    Transaction updateTransactionInValid(Long transactionId);

    Transaction deleteTransaction(Long id);

    Transaction createTransactionWithAccount( TransactionAccountRequestDTO transactions);
}
