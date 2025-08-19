package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long enrollmentId;

    @NotNull(message = "ClassEntity ID is required")
    private Long classId;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    private LocalDate enrollmentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Additional fields for display
    private String className;
    private String studentName;
    private String branchName;
}
