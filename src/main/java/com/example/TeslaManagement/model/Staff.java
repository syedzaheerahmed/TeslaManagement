package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long staff_id;
    private String staff_name;
    private long branch_id;
    private String address;
    private String contact_number;
    private boolean is_teaching_staff;
    private boolean is_admin;
    private boolean is_active;
    private String reason_for_deactivation;
}
