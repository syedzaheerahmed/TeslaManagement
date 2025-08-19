package com.example.TeslaManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSessionViewDTO {
    private Long classSessionId;
    private Long classId;
    private String className;
    private String batchTimings;
    private LocalDate sessionDate;
    private Long staffId;
    private String staffName;
    private List<StudentForAttendanceDTO> students;
    private Boolean isAttendanceMarked;
    private Integer totalStudents;
    private Integer presentCount;
    private Integer absentCount;
}
