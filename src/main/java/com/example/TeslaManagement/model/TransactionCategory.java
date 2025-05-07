package com.example.TeslaManagement.model;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

/**
 * The persistent class for the transaction_categories database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction_categories")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class TransactionCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private Long categoryId;

	@Column(name="category_name")
	private String categoryName;
}