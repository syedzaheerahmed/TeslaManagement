package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchAttendanceOverviewDTO {
    private Long branchId;
    private String branchName;
    private Integer totalClasses;
    private Integer totalStudents;
    private Double overallAttendancePercentage;
    private List<ClassesDTO> classes;
    private List<MonthlyAttendanceReportDTO> monthlyReports;
}
