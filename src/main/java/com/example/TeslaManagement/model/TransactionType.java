package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


/**
 * The persistent class for the transaction_types database table.
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction_types")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TransactionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="type_id")
	private Long typeId;

	@Column(name="type_name")
	private String typeName;

}