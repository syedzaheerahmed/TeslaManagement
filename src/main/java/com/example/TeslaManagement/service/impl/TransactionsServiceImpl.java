package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.model.Transactions;
import com.example.TeslaManagement.repository.TransactionsRepo;
import com.example.TeslaManagement.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    private final TransactionsRepo transactionsRepository;

    @Autowired
    public TransactionsServiceImpl(TransactionsRepo transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public Transactions createTransaction(Transactions transaction) {
        return transactionsRepository.save(transaction);
    }

    @Override
    public Transactions getTransactionById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Transactions updateTransaction(Long id, Transactions transaction) {
        Transactions existingTransaction = getTransactionById(id);
        existingTransaction.setTransactionType(transaction.getTransactionType());
        existingTransaction.setCategory(transaction.getCategory());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setPaymentMode(transaction.getPaymentMode());
        existingTransaction.setInvalidTransaction(transaction.isInvalidTransaction());
        existingTransaction.setTransactionDate(transaction.getTransactionDate());
        existingTransaction.setComments(transaction.getComments());
        existingTransaction.setBranch(transaction.getBranch());
        existingTransaction.setStaff(transaction.getStaff());
        existingTransaction.setStudent(transaction.getStudent());
        existingTransaction.setReceivedBy(transaction.getReceivedBy());
        return transactionsRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        Transactions transaction = getTransactionById(id);
        transactionsRepository.delete(transaction);
    }
}

