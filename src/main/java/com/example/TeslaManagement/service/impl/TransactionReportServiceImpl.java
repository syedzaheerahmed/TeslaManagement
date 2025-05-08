package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.DTO.TransactionDetailDTO;
import com.example.TeslaManagement.DTO.TransactionReportDTO;
import com.example.TeslaManagement.model.Transaction;
import com.example.TeslaManagement.repository.TransactionsRepo;
import com.example.TeslaManagement.service.TransactionReportService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TransactionReportServiceImpl implements TransactionReportService {
    @Autowired
    private TransactionsRepo transactionsRepository;

    @Transactional()//readOnly = true
    public TransactionReportDTO generateTransactionReport(Date fromDate) {
        // Fetch valid transactions (not invalid) from the given date
        List<Transaction> validTransactions = transactionsRepository.findByTransactionDateGreaterThanEqualAndIsInvalidTransactionFalse(fromDate);

        TransactionReportDTO report = new TransactionReportDTO();
        List<TransactionDetailDTO> transactionDetails = new ArrayList<>();

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpenses = BigDecimal.ZERO;

        for (Transaction transaction : validTransactions) {
            TransactionDetailDTO detailDTO = mapTransactionToDetailDTO(transaction);
            transactionDetails.add(detailDTO);

            // Calculate Income and Expenses
            if (isCreditTransaction(detailDTO)) {
                totalIncome = totalIncome.add(transaction.getAmount());
            } else {
                totalExpenses = totalExpenses.add(transaction.getAmount());
            }
        }

        // Populate Report DTO
        report.setTransactions(transactionDetails);
        report.setTotalIncome(totalIncome);
        report.setTotalExpenses(totalExpenses);
        report.setNetBalance(totalIncome.subtract(totalExpenses));
        report.setTotalTransactions(transactionDetails.size());
        report.setCreditTransactions(countCreditTransactions(transactionDetails));
        report.setDebitTransactions(countDebitTransactions(transactionDetails));

        return report;
    }

    private TransactionDetailDTO mapTransactionToDetailDTO(Transaction transaction) {
        TransactionDetailDTO detailDTO = new TransactionDetailDTO();
        detailDTO.setTransactionId(transaction.getTransactionId());
//        detailDTO.setTransactionType(transaction.getTransactionType());
//        detailDTO.setCategory(transaction.getCategory());
//        detailDTO.setAmount(transaction.getAmount());
//        detailDTO.setTransactionDate(transaction.getTransactionDate());
//        detailDTO.setPaymentMode(transaction.getPaymentMode());
//        detailDTO.setComments(transaction.getComments());
//
//        //TransactionReportDTO.builder().debitTransactions(5).build(); builder example
//
//        Objects.nonNull(transaction.getBranch()); // not null example
//
//        // Set optional related entities
//        if (transaction.getBranch() != null) {
//            detailDTO.setBranchName(transaction.getBranch().getBranch_name());
//        }
//        if (transaction.getStaff() != null) {
//            detailDTO.setStaffName(transaction.getStaff().getStaff_name());
//        }
//        if (transaction.getStudent() != null) {
//            detailDTO.setStudentName(transaction.getStudent().getStudent_name());
//        }

        return detailDTO;
    }

    private int countCreditTransactions(List<TransactionDetailDTO> transactions) {
        return (int) transactions.stream()
                .filter(this::isCreditTransaction)
                .count();
    }

    private int countDebitTransactions(List<TransactionDetailDTO> transactions) {
        return (int) transactions.stream()
                .filter(transaction -> !isCreditTransaction(transaction))
                .count();
    }

    private boolean isCreditTransaction(TransactionDetailDTO transaction) {
        // Customize this logic based on your business rules for credit transactions
        List<String> creditCategories = Arrays.asList(
                "TUITION", "FEES", "SCHOLARSHIP", "DONATION", "REFUND", "INCOME", "INVESTMENT"
        );
        return creditCategories.contains(transaction.getCategory());
    }
}