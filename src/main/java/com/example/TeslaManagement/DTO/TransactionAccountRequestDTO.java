package com.example.TeslaManagement.DTO;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.*;


public class TransactionAccountRequestDTO {
    // Transaction Details
    @NotBlank(message = "Transaction type is required")
    private String transactionType;

    @NotBlank(message = "Category is required")
    private String category;

    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Payment mode is required")
    private String paymentMode;

    @NotNull(message = "Transaction date is required")
    private Date transactionDate;

   // @Length(max = 500, message = "Transaction comments cannot exceed 500 characters")
    private String transactionComments;

    // Additional Account Details
    private String accountComments;

    // Associated IDs
    @NotNull(message = "Branch ID is required")
    private Long branchId;
    private Long staffId;
    private Long studentId;

    @NotNull(message = "Received By ID is required")
    private Long receivedById;


    public TransactionAccountRequestDTO(String transactionType, String category, BigDecimal amount, String paymentMode, Date transactionDate, String transactionComments, String accountComments, Long staffId, Long branchId, Long studentId, Long receivedById) {
        this.transactionType = transactionType;
        this.category = category;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.transactionDate = transactionDate;
        this.transactionComments = transactionComments;
        this.accountComments = accountComments;
        this.staffId = staffId;
        this.branchId = branchId;
        this.studentId = studentId;
        this.receivedById = receivedById;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionComments() {
        return transactionComments;
    }

    public void setTransactionComments(String transactionComments) {
        this.transactionComments = transactionComments;
    }

    public String getAccountComments() {
        return accountComments;
    }

    public void setAccountComments(String accountComments) {
        this.accountComments = accountComments;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getReceivedById() {
        return receivedById;
    }

    public void setReceivedById(Long receivedById) {
        this.receivedById = receivedById;
    }
}