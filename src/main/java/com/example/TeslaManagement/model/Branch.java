package com.example.TeslaManagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="branches")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="branch_id")
	private Long branchId;

	@Column(name="branch_address", nullable = false)
	private String branchAddress;

	@Column(name="branch_name", nullable = false)
	private String branchName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hq_id", nullable = false)
	private HQ hq;

}