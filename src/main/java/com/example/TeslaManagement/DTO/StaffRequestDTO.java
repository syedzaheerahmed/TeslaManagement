package com.example.TeslaManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffRequestDTO {
//    @NotNull(message = "User ID is required")
//    @Positive(message = "User ID must be positive")
//    private Long userId;

    @NotNull(message = "Branch ID is required")
    @Positive(message = "Branch ID must be positive")
    private Long branchId;

    @NotBlank(message = "Staff name is required")
    private String staffName;

    private String address;

    @Pattern(regexp = "^[+]?[0-9]{9,10}$", message = "Contact number must be 10-15 digits")
    private String contactNumber;

    private boolean isTeachingStaff;

    private boolean isActive = true;

    private boolean isSalaryPaid = false;

    private String reasonForDeactivation;

    private Boolean isAdmin = false;

    // Optional: If you want to allow custom username (otherwise auto-generated)
    private String customUsername;
}
