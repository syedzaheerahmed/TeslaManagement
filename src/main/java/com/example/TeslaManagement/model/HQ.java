package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "HQtable")
public class HQ {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long hq_id;
    private String hq_name;
    private String address;
}
