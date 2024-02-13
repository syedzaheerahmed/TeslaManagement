package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long user_id;
    private String username;
    private String password;
    private long branch_id;
    private String role;
    private long staff_id;
}
