package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "accounts_id")
    private Long accountId;

    @Column(name = "mode")
    private String mode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    private Branches branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @Column(name = "date")
    private Date date;

    @Column(name = "income_type")
    private String incomeType;

    @Column(name = "income_amount")
    private BigDecimal incomeAmount;

    @Column(name = "payment_mode")
    private String paymentMode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_by", referencedColumnName = "user_id")
    private Roles receivedBy;

    private String comments;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

    public Student getStudent() {
        return student;
    }

    public Accounts() {
    }

    public Accounts(Long accountId, String mode, Branches branch, Student student, Date date, String incomeType, BigDecimal incomeAmount, String paymentMode, Roles receivedBy, String comments) {
        this.accountId = accountId;
        this.mode = mode;
        this.branch = branch;
        this.student = student;
        this.date = date;
        this.incomeType = incomeType;
        this.incomeAmount = incomeAmount;
        this.paymentMode = paymentMode;
        this.receivedBy = receivedBy;
        this.comments = comments;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Roles getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(Roles receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
