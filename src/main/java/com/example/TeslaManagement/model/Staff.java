package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "staff")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "staff_name", nullable = false)
    private String staffName;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isActive;

    @Column(name = "is_teaching_staff", nullable = false)
    private boolean isTeachingStaff;

    @Column(name = "reason_for_deactivation")
    private String reasonForDeactivation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user; //Links to users table for login access

    @Column(name = "is_salary_paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isSalaryPaid;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}