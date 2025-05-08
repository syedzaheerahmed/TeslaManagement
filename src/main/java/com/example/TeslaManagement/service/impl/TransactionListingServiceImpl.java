package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.DTO.TransactionListingDTO;
import com.example.TeslaManagement.model.Transaction;
import com.example.TeslaManagement.repository.TransactionsRepo;
import com.example.TeslaManagement.service.TransactionListingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionListingServiceImpl implements TransactionListingService {
    @Autowired
    private TransactionsRepo transactionsRepository;

//    @Autowired
//    private UserDetails userDetails;
//
//    @Autowired
//    private ModelMapper modelMapper;

    @Transactional()//readOnly = true
    public List<TransactionListingDTO> getTransactionsList(Date fromDate) {
        // Fetch all transactions from the given date
        List<Transaction> transactions = transactionsRepository.findTransactionsFromDate(fromDate);

        // Map to DTO
        return transactions.stream()
                .map(this::mapToTransactionListingDTO)
                .collect(Collectors.toList());
    }

    private TransactionListingDTO mapToTransactionListingDTO(Transaction transaction) {
        TransactionListingDTO dto = new TransactionListingDTO();

        // Basic Transaction Details
//        dto.setTransactionId(transaction.getTransactionId());
//        dto.setTransactionType(transaction.getTransactionType());
//        dto.setCategory(transaction.getCategory());
//        dto.setAmount(transaction.getAmount());
//        dto.setTransactionDate(transaction.getTransactionDate());
//        dto.setPaymentMode(transaction.getPaymentMode());
//        dto.setComments(transaction.getComments());
//        dto.setInvalidTransaction(transaction.isInvalidTransaction());
//
//        // Branch Details
//        if (transaction.getBranch() != null) {
//            dto.setBranchId(transaction.getBranch().getBranch_id());
//            dto.setBranchName(transaction.getBranch().getBranch_name());
//        }
//
//        // Staff Details
//        if (transaction.getStaff() != null) {
//            dto.setStaffId(transaction.getStaff().getStaff_id());
//            dto.setStaffName(transaction.getStaff().getStaff_name());
//        }
//
//        // Student Details
//        if (transaction.getStudent() != null) {
//            dto.setStudentId(transaction.getStudent().getStudent_id());
//            dto.setStudentName(transaction.getStudent().getStudent_name());
//        }
//
//        // Transaction Made By Details
//        if (transaction.getTransactionMadeBy() != null) {
//            dto.setTransactionMadeById(transaction.getTransactionMadeBy().getId());
////            dto.setTransactionMadeByName(userDetails.getUsername().);
//        }

        return dto;
    }
}
