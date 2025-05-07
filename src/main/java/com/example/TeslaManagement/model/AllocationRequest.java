package com.example.TeslaManagement.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


/**
 * The persistent class for the allocation_requests database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="allocation_requests")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class AllocationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="request_id")
	private Long requestId;

	@Column(name="approved_by")
	private Long approvedBy;

	@Column(name="branch_id")
	private Long branchId;

	private String comments;

	@CreationTimestamp
	@Column(name="created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name="entity_id")
	private Long entityId;

	@Column(name="entity_type")
	private String entityType;

	@Column(name="request_status")
	private String requestStatus;

	@Column(name="request_type")
	private String requestType;

	@Column(name="requested_by")
	private Long requestedBy;

	@UpdateTimestamp
	@Column(name="updated_at", nullable = false)
	private Timestamp updatedAt;

}