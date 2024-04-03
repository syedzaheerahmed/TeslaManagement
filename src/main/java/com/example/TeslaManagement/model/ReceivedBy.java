package com.example.TeslaManagement.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "received_by")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ReceivedBy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long receivedById;

    private String receivedByName;
}
