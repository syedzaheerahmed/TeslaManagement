package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * The persistent class for the enrollments database table.
 * 
 */
@Entity
@Table(
		name = "enrollments",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "unique_student_class",
						columnNames = {"student_id", "class_id"}
				)
		}
)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Long enrollmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
	private ClassEntity classEntity;

	@Column(name = "enrollment_date", nullable = false)
	private LocalDate enrollmentDate;

	@Column(name = "is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean isActive;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}