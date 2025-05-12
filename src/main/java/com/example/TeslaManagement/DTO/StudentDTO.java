package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

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

    private Long branchId;

    private Long userId;

    private boolean isActive;

    private boolean isApproved;

    private boolean isFeesPaid;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}