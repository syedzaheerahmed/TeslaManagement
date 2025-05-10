package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * The persistent class for the user_roles database table.
 * 
 */

@Entity
@Table(
		name = "user_roles",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "unique_user_role_branch",
						columnNames = {"user_id", "role_id", "branch_id"}
				)
		}
)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "role", "branch"})
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private Long userRoleId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = true)
	private Branch branch; // NULL for Super Admin, required for branch-specific roles

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Roles role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}