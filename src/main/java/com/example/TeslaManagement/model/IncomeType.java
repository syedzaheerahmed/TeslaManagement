package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "income_type")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class IncomeType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long incomeId;

    private String incomeTypeName;
}
