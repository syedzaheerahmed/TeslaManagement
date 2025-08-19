package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentLowAttendanceDTO {
    private Long studentId;
    private String studentName;
    private Double attendancePercentage;
    private String parentContact;
    private String reason;
}
