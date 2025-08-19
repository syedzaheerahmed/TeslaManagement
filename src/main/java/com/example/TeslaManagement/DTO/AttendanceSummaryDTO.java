package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSummaryDTO {
    private Long classId;
    private String className;
    private Long totalSessions;
    private Long totalStudentDays;
    private Long totalPresent;
    private Long totalAbsent;
    private Double overallAttendancePercentage;
}
