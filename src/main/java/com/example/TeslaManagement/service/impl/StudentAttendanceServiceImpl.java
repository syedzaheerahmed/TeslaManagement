package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.StudentAttendance;
import com.example.TeslaManagement.repository.StudentAttendanceRepo;
import com.example.TeslaManagement.service.StudentAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {
    @Autowired
    StudentAttendanceRepo studentAttendanceRepo;
    @Override
    public String createStudentAttendance(StudentAttendance studentAttendance) {
        try{
            studentAttendanceRepo.save(studentAttendance);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding attendance details";
        }
        return "Attendance details added successfully";
    }

    @Override
    public String updateStudentAttendance(StudentAttendance studentAttendance) {
        try{
            studentAttendanceRepo.save(studentAttendance);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating attendance details";
        }
        return "Attendance details updated successfully";
    }

    @Override
    public String deleteStudentAttendance(Long student_attendance_id) {
        try{
            studentAttendanceRepo.deleteById(student_attendance_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting attendance details";
        }
        return "Attendance details deleted successfully";
    }

    @Override
    public StudentAttendance getStudentAttendanceDetails(Long student_attendance_id) {
        try {
            if(studentAttendanceRepo.findById(student_attendance_id).isPresent()) {
                return studentAttendanceRepo.getReferenceById(student_attendance_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<StudentAttendance> getAllStudentAttendance() {
        return studentAttendanceRepo.findAll();
    }
}
