package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffDTO {
    private Long staffId;
    private String staffName;
    private String address;
    private String contactNumber;
    private boolean isActive;
    private boolean isTeachingStaff;
    private String reasonForDeactivation;
    private Long branchId;
    private String branchName;
    private Long userId;
    private String username;
    private boolean isSalaryPaid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
