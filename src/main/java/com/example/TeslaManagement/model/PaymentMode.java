package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment_mode")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PaymentMode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentModeId;

    private String paymentModeName;
}
