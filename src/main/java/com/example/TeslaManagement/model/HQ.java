package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "HQtable")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class HQ {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long hq_id;
    public String hq_name;
    public String address;
}
