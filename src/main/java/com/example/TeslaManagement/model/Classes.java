package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long class_id;
    private String batch_name;
    private long branch_id;
    private String batch_timings;
    private long staff_id;
}
