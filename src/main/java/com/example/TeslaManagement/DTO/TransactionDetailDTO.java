package com.example.TeslaManagement.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDetailDTO {
    private Long transactionId;
    private String transactionType;
    private String category;
    private BigDecimal amount;
    private Date transactionDate;
    private String paymentMode;
    private String comments;
    private String branchName;
    private String staffName;
    private String studentName;

    public TransactionDetailDTO() {
    }

    public TransactionDetailDTO(Long transactionId, String transactionType, String category, BigDecimal amount, Date transactionDate, String paymentMode, String branchName, String comments, String staffName, String studentName) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.category = category;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.paymentMode = paymentMode;
        this.branchName = branchName;
        this.comments = comments;
        this.staffName = staffName;
        this.studentName = studentName;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}