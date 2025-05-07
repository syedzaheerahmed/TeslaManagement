package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Long enrollmentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
	private Class classEntity;

	@Column(name = "enrollment_date", nullable = false)
	private LocalDate enrollmentDate;

//	/**
//	 * Called before persisting a new entity to set default enrollment date if not specified
//	 */
//	@PrePersist
//	protected void onCreate() {
//		if (this.enrollmentDate == null) {
//			this.enrollmentDate = LocalDate.now();
//		}
//	}
}