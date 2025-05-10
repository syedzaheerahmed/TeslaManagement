package com.example.TeslaManagement.model;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * The persistent class for the transaction_categories database table.
 * 
 */
@Entity
@Table(name="transaction_categories")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategory{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private Long categoryId;

	@Column(name="category_name", nullable = false, unique = true)
	private String categoryName;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
//'Fees', 'Salary', 'Maintenance'