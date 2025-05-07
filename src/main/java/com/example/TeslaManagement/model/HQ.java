package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HQtable")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class HQ {
    @Id
    @Column(name="hq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long hq_id;
    @Column(name="hq_name", nullable = false)
    public String hq_name;
    @Column(name="address", nullable = false)
    public String address;
}
