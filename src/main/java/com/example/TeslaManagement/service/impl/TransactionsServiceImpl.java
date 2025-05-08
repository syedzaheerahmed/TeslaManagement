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

import jakarta.validation.Valid;
import java.util.List;

@Service
public class TransactionsServiceImpl implements TransactionsService {

    @Autowired
    private final TransactionsRepo transactionsRepository;

    @Autowired
    private BranchRepo branchRepository;

    @Autowired
    private StaffRepo staffRepository;

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private UserInfoRepo userInfoRepository;

    @Autowired
    public TransactionsServiceImpl(TransactionsRepo transactionsRepository) {
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionsRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    @Override
    public Transaction updateTransaction(Long id, Transaction transaction) {
        Transaction existingTransaction = getTransactionById(id);
        existingTransaction.setTransactionType(transaction.getTransactionType());
        existingTransaction.setCategory(transaction.getCategory());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setPaymentMode(transaction.getPaymentMode());
//        existingTransaction.setInvalidTransaction(transaction.isInvalidTransaction());
//        existingTransaction.setTransactionDate(transaction.getTransactionDate());
//        existingTransaction.setComments(transaction.getComments());
        existingTransaction.setBranch(transaction.getBranch());
        existingTransaction.setStaff(transaction.getStaff());
        existingTransaction.setStudent(transaction.getStudent());
        existingTransaction.setTransactionMadeBy(transaction.getTransactionMadeBy());
        return transactionsRepository.save(existingTransaction);
    }

    @Override
    public Transaction updateTransactionInValid(Long transactionId){
        Transaction existingTransaction = getTransactionById(transactionId);
       // existingTransaction.setInvalidTransaction(true);
        return transactionsRepository.save(existingTransaction);
    }

    @Override
    public Transaction deleteTransaction(Long id) {
        Transaction transaction = getTransactionById(id);
      //  transaction.setDelete(true);
        return transactionsRepository.save(transaction);
    }

    @Transactional
    public Transaction createTransactionWithAccount(@Valid TransactionAccountRequestDTO requestDTO) {


        // Validate References
        Branch branch = branchRepository.findById(requestDTO.getBranchId())
                .orElseThrow(() -> new ReferenceNotFoundException("Branch not found"));

        User receivedBy = userInfoRepository.findById(requestDTO.getTransactionMadeBy())
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
        Transaction transaction = new Transaction();
//        transaction.setTransactionType(requestDTO.getTransactionType());
//        transaction.setCategory(requestDTO.getCategory());
//        transaction.setAmount(requestDTO.getAmount());
//        transaction.setPaymentMode(requestDTO.getPaymentMode());
//        transaction.setTransactionDate(requestDTO.getTransactionDate());
        transaction.setComments(requestDTO.getTransactionComments());
        transaction.setBranch(branch);
        transaction.setTransactionMadeBy(receivedBy);

        // Set associated entities
        if (staff != null) transaction.setStaff(staff);
        if (student != null) transaction.setStudent(student);

        // Save Transaction First
        Transaction savedTransaction = transactionsRepository.save(transaction);
        System.out.printf("Transaction created with ID: {}", savedTransaction.getTransactionId());

//        // Create Account
//        Accounts account = new Accounts();
//        account.setTransaction(savedTransaction);
//        account.setDate(requestDTO.getTransactionDate());
//        account.setReceivedBy(transaction.getTransactionMadeBy());
//        account.setComments(requestDTO.getAccountComments());
//
//        // Save Account
//        accountRepository.save(account);

        return savedTransaction;
    }


}

