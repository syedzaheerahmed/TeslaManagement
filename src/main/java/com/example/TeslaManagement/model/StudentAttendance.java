package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "student_attendance")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public long student_id;
    public long class_id;
    public long branch_id;
    public Date date = new Date();
    public long staff_id;
    public boolean is_present;

}
