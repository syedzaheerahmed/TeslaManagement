package com.example.TeslaManagement.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    // Student details
    private Long studentId;
    private String studentName;
    private LocalDate dob;
    private String gender;
    private String studentAddress;
    private String parentName;
    private String parentContact;
    private String schoolName;
    private String schoolStd;
    private String boardOfSchool;
    private Integer batchYear;
    private boolean isActive;
    private boolean isApproved;
    private boolean isFeesPaid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // User details
    private Long userId;
    private String username;
    private String temporaryPassword; // Will be shown only once for security
    private boolean isUserActive;
    private LocalDateTime userCreatedAt;

    // Branch details
    private Long branchId;
    private String branchName;

    // Creator details
    private String createdByUsername;

    // Helper method to hide password after first access
    public void hideTemporaryPassword() {
        this.temporaryPassword = "****";
    }
}