package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches branch;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    private Mode mode;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "income_type_id")
    private IncomeType incomeType;

    private BigDecimal incomeAmount;

    @ManyToOne
    @JoinColumn(name = "payment_mode_id")
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "received_by_id")
    private ReceivedBy receivedBy;

    private String comments;
}
