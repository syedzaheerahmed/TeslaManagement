package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "staff")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_teaching_staff", nullable = false)
    private Boolean isTeachingStaff;

    @Column(name = "reason_for_deactivation")
    private String reasonForDeactivation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user; //Links to users table for login access

    @Column(name = "is_salary_paid", nullable = false)
    private Boolean isSalaryPaid = false;
}