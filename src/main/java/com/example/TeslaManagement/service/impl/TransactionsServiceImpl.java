package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.ReferenceNotFoundException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.TransactionAccountRequestDTO;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.TransactionsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private final TransactionsRepo transactionsRepository;

    @Autowired
    private AccountsRepo accountRepository;

    @Autowired
    private BranchesRepo branchRepository;

    @Autowired
    private StaffRepo staffRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private UserDetailsRepo userDetailsRepository;

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

    @Transactional
    public Transactions createTransactionWithAccount(@Valid TransactionAccountRequestDTO requestDTO) {


        // Validate References
        Branches branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new ReferenceNotFoundException("Branch not found"));

        UserDetails receivedBy = userDetailsRepository.findById(requestDTO.getReceivedById())
                .orElseThrow(() -> new ReferenceNotFoundException("User not found"));

        Staff staff = null;
        Student student = null;

        if (requestDTO.getStaffId() != null) {
            staff = staffRepository.findById(requestDTO.getStaffId())
                    .orElseThrow(() -> new ReferenceNotFoundException("Staff not found"));
        }

        if (requestDTO.getStudentId() != null) {
            student = studentRepository.findById(requestDTO.getStudentId())
                    .orElseThrow(() -> new ReferenceNotFoundException("Student not found"));
        }



        // Create Transaction
        Transactions transaction = new Transactions();
        transaction.setTransactionType(requestDTO.getTransactionType());
        transaction.setCategory(requestDTO.getCategory());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setPaymentMode(requestDTO.getPaymentMode());
        transaction.setTransactionDate(requestDTO.getTransactionDate());
        transaction.setComments(requestDTO.getTransactionComments());
        transaction.setBranch(branch);
        transaction.setReceivedBy(receivedBy);

        // Set associated entities
        if (staff != null) transaction.setStaff(staff);
        if (student != null) transaction.setStudent(student);

        // Save Transaction First
        Transactions savedTransaction = transactionsRepository.save(transaction);
        System.out.printf("Transaction created with ID: {}", savedTransaction.getTransactionId());

        // Create Account
        Accounts account = new Accounts();
        account.setTransaction(savedTransaction);
        account.setDate(requestDTO.getTransactionDate());
        account.setReceivedBy(transaction.getReceivedBy());
        account.setComments(requestDTO.getAccountComments());

        // Save Account
        accountRepository.save(account);

        return savedTransaction;
    }
}

