package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * The persistent class for the class_sessions database table.
 * 
 */
@Entity
@Table(
		name = "class_sessions",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "unique_class_session",
						columnNames = {"class_id", "session_date"}
				)
		}
)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "class_session_id")
	private Long classSessionId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
	private ClassEntity classEntity;

	@Column(name = "session_date", nullable = false)
	private LocalDate sessionDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
	private Staff staff;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}