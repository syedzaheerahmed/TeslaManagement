package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassesDTO {
    private Long classId;

    @NotBlank(message = "ClassEntity name is required")
    private String className;

    @NotBlank(message = "ClassEntity timings are required")
    private String classTimings;

    @NotNull(message = "Branch ID is required")
    private Long branchId;

    @NotNull(message = "Staff ID is required")
    private Long staffId;

    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
