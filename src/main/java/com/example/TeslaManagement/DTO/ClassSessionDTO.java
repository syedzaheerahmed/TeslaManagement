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
public class ClassSessionDTO {
    private Long classSessionId;

    @NotNull(message = "ClassEntity ID is required")
    private Long classId;

    @NotNull(message = "Staff ID is required")
    private Long staffId;

    @NotNull(message = "Session date is required")
    private LocalDate sessionDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Additional fields for display
    private String className;
    private String staffName;
}