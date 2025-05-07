package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_attendance")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="attendance_id")
    private Long attendanceId;

    @Column(name="class_session_id")
    private Long classSessionId;

    @Column(name="is_present")
    private Boolean isPresent;

    @Column(name="student_id")
    private Long studentId;
}
