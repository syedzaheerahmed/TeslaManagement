package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a financial transaction in the system
 */
@Entity
@Table(name = "transactions")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount must be non-negative")
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    // Many-to-One relationship with TransactionCategory
    @NotNull(message = "Transaction category cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
    private TransactionCategory category;

    @Column(name = "comments")
    private String comments;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_invalid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isInvalid;

    // Many-to-One relationship with PaymentMode
    @NotNull(message = "Payment mode cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_mode_id", referencedColumnName = "mode_id", nullable = false)
    private PaymentMode paymentMode;

    @NotNull(message = "Transaction date cannot be null")
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    // Many-to-One relationship with TransactionType
    @NotNull(message = "Transaction type cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "type_id", nullable = false)
    private TransactionType transactionType;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Many-to-One relationship with Branch
    @NotNull(message = "Branch cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false)
    private Branch branch;

    // Many-to-One relationship with Staff (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staff;

    // Many-to-One relationship with Student (optional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    // Many-to-One relationship with User (who made the transaction)
    @NotNull(message = "Transaction creator cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_made_by", referencedColumnName = "user_id", nullable = false)
    private User transactionMadeBy;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isDeleted;

}