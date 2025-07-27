package com.example.TeslaManagement.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffWithUserResponseDTO {
    private Long staffId;
    private String staffName;
    private String address;
    private String contactNumber;
    private boolean isActive;
    private boolean isTeachingStaff;
    private boolean isAdmin;
    private boolean isSalaryPaid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String reasonForDeactivation;

    // Branch information
    private Long branchId;
    private String branchName;

    // User details
    private Long userId;
    private String username;
    private String temporaryPassword; // Will be shown only once for security
    private boolean isUserActive;
    private LocalDateTime userCreatedAt;

    // Creator details
    private String createdByUsername;
}
