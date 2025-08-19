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
public class StudentAttendanceReportDTO {
    private Long studentId;
    private String studentName;
    private Long classId;
    private String className;
    private Double attendancePercentage;
    private Integer currentStreak;
    private Integer totalPresent;
    private Integer totalSessions;
    private List<AttendanceRecordDTO> recentAttendance;
}
