package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Transactions {
    @Id
    @Column(name="transaction_id")
    private Long transactionId;

    private BigDecimal amount;

    @Column(name="branch_id")
    private Long branchId;

    @Column(name="category_id")
    private Long categoryId;

    private String comments;

    @Column(name="created_at")
    private Timestamp createdAt;

    private Timestamp date;

    @Column(name="is_deleted")
    private Boolean isDeleted;

    @Column(name="is_invalid")
    private Boolean isInvalid;

    @Column(name="payment_mode_id")
    private Long paymentModeId;

    @Column(name="staff_id")
    private Long staffId;

    @Column(name="student_id")
    private Long studentId;

    @Column(name="transaction_made_by")
    private Long transactionMadeBy;

    @Column(name="transaction_type_id")
    private Long transactionTypeId;

    @Column(name="updated_at")
    private Timestamp updatedAt;
}
