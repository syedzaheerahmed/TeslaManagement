package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "mode")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Mode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long modeId;

    private String modeName;
}
