package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.StudentAttendance;
import java.util.List;

public interface StudentAttendanceService {
    String createStudentAttendance(StudentAttendance studentAttendance);
    String updateStudentAttendance(StudentAttendance studentAttendance);
    String deleteStudentAttendance(Long student_attendance_id);
    StudentAttendance getStudentAttendanceDetails(Long student_attendance_id);
    List<StudentAttendance> getAllStudentAttendance();
}
