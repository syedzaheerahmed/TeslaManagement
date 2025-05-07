package com.example.TeslaManagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


/**
 * The persistent class for the payment_modes database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payment_modes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PaymentMode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="mode_id")
	private Long modeId;

	@Column(name="mode_name")
	private String modeName;

}