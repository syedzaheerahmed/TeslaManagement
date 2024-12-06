package com.example.TeslaManagement.DTO;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionListingDTO {
    private Long transactionId;
    private String transactionType;
    private String category;
    private BigDecimal amount;
    private Date transactionDate;
    private String paymentMode;
    private String comments;
    private boolean isInvalidTransaction;

    // Branch Details
    private Long branchId;
    private String branchName;

    // Staff Details
    private Long staffId;
    private String staffName;

    // Student Details
    private Long studentId;
    private String studentName;

    // Transaction Made By
    private Long transactionMadeById;
    private String transactionMadeByName;


    public TransactionListingDTO() {
    }

    public TransactionListingDTO(Long transactionId, String transactionType, String category, BigDecimal amount, Date transactionDate, String paymentMode, String comments, boolean isInvalidTransaction, Long branchId, String branchName, Long staffId, String staffName, Long studentId, String studentName, Long transactionMadeById, String transactionMadeByName) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.category = category;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.paymentMode = paymentMode;
        this.comments = comments;
        this.isInvalidTransaction = isInvalidTransaction;
        this.branchId = branchId;
        this.branchName = branchName;
        this.staffId = staffId;
        this.staffName = staffName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.transactionMadeById = transactionMadeById;
        this.transactionMadeByName = transactionMadeByName;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public boolean isInvalidTransaction() {
        return isInvalidTransaction;
    }

    public void setInvalidTransaction(boolean invalidTransaction) {
        isInvalidTransaction = invalidTransaction;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getTransactionMadeById() {
        return transactionMadeById;
    }

    public void setTransactionMadeById(Long transactionMadeById) {
        this.transactionMadeById = transactionMadeById;
    }

    public String getTransactionMadeByName() {
        return transactionMadeByName;
    }

    public void setTransactionMadeByName(String transactionMadeByName) {
        this.transactionMadeByName = transactionMadeByName;
    }
}
