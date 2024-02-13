package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "student_attendance")
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long student_id;

    private long class_id;

    private long branch_id;

    private Date date = new Date();

    private long staff_id;

    private boolean is_present;

}
