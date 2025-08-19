package com.example.TeslaManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {

    // Student personal details
    @NotBlank(message = "Student name is mandatory")
    private String studentName;

    private LocalDate dob;

    private String gender;

    private String studentAddress;

    private String parentName;

    private String parentContact;

    private String schoolName;

    private Long schoolStd;

    private Long boardOfSchool;

    private Integer batchYear;

    @NotNull(message = "Branch ID is mandatory")
    private Long branchId;

    private boolean isFeesPaid = false;

    // Optional: If you want to allow custom username (otherwise auto-generated)
    private String customUsername;
}