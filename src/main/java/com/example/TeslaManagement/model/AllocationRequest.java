package com.example.TeslaManagement.model;

import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * The persistent class for the allocation_requests database table.
 * 
 */
/**
 * Entity representing an allocation/deallocation request in the system
 */
@Entity
@Table(name = "allocation_requests")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocationRequest implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * Enumeration for request types
	 */
	public enum RequestType {
		Allocation, Deallocation
	}

	/**
	 * Enumeration for entity types
	 */
	public enum EntityType {
		Student, Faculty
	}

	/**
	 * Enumeration for request statuses
	 */
	public enum RequestStatus {
		Pending, Approved, Rejected
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long requestId;

	@NotNull(message = "Request type cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "request_type", nullable = false)
	private RequestType requestType;

	@NotNull(message = "Entity type cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "entity_type", nullable = false)
	private EntityType entityType;

	@NotNull(message = "Entity ID cannot be null")
	@Column(name = "entity_id", nullable = false)
	private Long entityId; //References student_id or staff_id

	// Many-to-One relationship with Branch
	@NotNull(message = "Branch cannot be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false)
	private Branch branch;

	// Many-to-One relationship with User (who requested)
	@NotNull(message = "Requester cannot be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requested_by", referencedColumnName = "user_id", nullable = false)
	private User requestedBy;

	// Many-to-One relationship with User (who approved)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approved_by", referencedColumnName = "user_id")
	private User approvedBy;

	@NotNull(message = "Request status cannot be null")
	@Enumerated(EnumType.STRING)
	@Column(name = "request_status", nullable = false)
	private RequestStatus requestStatus = RequestStatus.Pending;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Column(name = "comments")
	private String comments;

	/**
	 * Called before persisting a new entity
	 */
	@PrePersist
	protected void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.createdAt = now;
		this.updatedAt = now;

		// Set default request status if not provided
		if (this.requestStatus == null) {
			this.requestStatus = RequestStatus.Pending;
		}
	}

	/**
	 * Called before updating an existing entity
	 */
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	/**
	 * Helper method to get the actual entity based on entity_type
	 * Note: This needs to be implemented in a service layer
	 * as it involves loading different entity types
	 */
	public Object getActualEntity() {
		// This method would be implemented in a service class
		// It would load either a Student or Staff entity based on entityType and entityId
		return null;
	}
}