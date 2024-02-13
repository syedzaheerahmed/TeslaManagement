package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "branches")
public class Branches {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long branch_id;
    private String branch_name;
    private String branch_address;
    private long hq_id;
}
