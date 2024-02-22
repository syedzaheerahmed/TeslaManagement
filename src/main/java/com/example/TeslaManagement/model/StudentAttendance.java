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
    public long student_attendance_id;
    public long student_id;
    public long class_id;
    public long branch_id;
    public Date date = new Date();
    public long staff_id;
    public boolean is_present;

    StudentAttendance(long student_attendance_id, long student_id, long class_id, long branch_id, Date date, long staff_id, boolean is_present) {
        this.student_attendance_id = student_attendance_id;
        this.student_id = student_id;
        this.class_id = class_id;
        this.branch_id = branch_id;
        this.date = date;
        this.staff_id = staff_id;
        this.is_present = is_present;
    }
    public StudentAttendance() {
    }

    public StudentAttendance(long student_attendance_id) { this.student_attendance_id = student_attendance_id; }
    public void setStudent_attendance_id(long student_attendance_id) { this.student_attendance_id = student_attendance_id; }
    public long getStudent_id() {
        return student_id;
    }
    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }
    public long getClass_id() {
        return class_id;
    }
    public void setClass_id(long class_id) {
        this.class_id = class_id;
    }
    public long getBranch_id() {
        return branch_id;
    }
    public void setBranch_id(long branch_id) {
        this.branch_id = branch_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getStaff_id() {
        return staff_id;
    }
    public void setStaff_id(long staff_id) {
        this.staff_id = staff_id;
    }
    public boolean isIs_present() {
        return is_present;
    }
    public void setIs_present(boolean is_present) {
        this.is_present = is_present;
    }
}
