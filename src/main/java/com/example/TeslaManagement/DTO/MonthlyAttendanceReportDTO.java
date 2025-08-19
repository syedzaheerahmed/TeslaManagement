package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyAttendanceReportDTO {
    private Integer year;
    private Integer month;
    private String monthName;
    private Long branchId;
    private String branchName;
    private Long totalRecords;
    private Long presentCount;
    private Long absentCount;
    private Double attendancePercentage;
}

