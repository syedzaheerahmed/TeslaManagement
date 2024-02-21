package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.StudentAttendance;
import java.util.List;

public interface StudentAttendanceService {
    public String createStudentAttendance(StudentAttendance studentAttendance);
    public String updateStudentAttendance(StudentAttendance studentAttendance);
    public String deleteStudentAttendance(Long student_attendance_id);
    public StudentAttendance getStudentAttendanceDetails(Long student_attendance_id);
    public List<StudentAttendance> getAllStudentAttendance();
}
