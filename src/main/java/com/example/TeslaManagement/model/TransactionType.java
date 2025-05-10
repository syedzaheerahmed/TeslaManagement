package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


/**
 * The persistent class for the transaction_types database table.
 * 
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction_types")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TransactionType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="type_id", nullable = false)
	private Long typeId;

	@Column(name="type_name", nullable = false, unique = true)
	private String typeName;
}

//'Debit', 'Credit'