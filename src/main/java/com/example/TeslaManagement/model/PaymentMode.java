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
 * The persistent class for the payment_modes database table.
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment_modes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PaymentMode  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mode_id", nullable = false)
	private Long modeId;

	@Column(name="mode_name", nullable = false, unique = true)
	private String modeName;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
//'Cash', 'Card', 'Online'