package com.example.TeslaManagement.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionReportDTO {
    // Tally Summary
    private BigDecimal totalIncome;
    private BigDecimal totalExpenses;
    private BigDecimal netBalance;

    // Detailed Transactions
    private List<TransactionDetailDTO> transactions;

    // Additional Summary
    private int totalTransactions;
    private int creditTransactions;
    private int debitTransactions;

}
