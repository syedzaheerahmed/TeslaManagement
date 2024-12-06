package com.example.TeslaManagement.DTO;

import java.math.BigDecimal;
import java.util.List;

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

    public TransactionReportDTO() {
    }

    public TransactionReportDTO(BigDecimal totalIncome, BigDecimal totalExpenses, BigDecimal netBalance, List<TransactionDetailDTO> transactions, int creditTransactions, int totalTransactions, int debitTransactions) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.netBalance = netBalance;
        this.transactions = transactions;
        this.creditTransactions = creditTransactions;
        this.totalTransactions = totalTransactions;
        this.debitTransactions = debitTransactions;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getNetBalance() {
        return netBalance;
    }

    public void setNetBalance(BigDecimal netBalance) {
        this.netBalance = netBalance;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public List<TransactionDetailDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetailDTO> transactions) {
        this.transactions = transactions;
    }

    public int getCreditTransactions() {
        return creditTransactions;
    }

    public void setCreditTransactions(int creditTransactions) {
        this.creditTransactions = creditTransactions;
    }

    public int getDebitTransactions() {
        return debitTransactions;
    }

    public void setDebitTransactions(int debitTransactions) {
        this.debitTransactions = debitTransactions;
    }
}
