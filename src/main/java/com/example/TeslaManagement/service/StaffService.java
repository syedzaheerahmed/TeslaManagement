package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Roles;
import com.example.TeslaManagement.model.Staff;
import java.util.List;

public interface StaffService {
    Roles createStaff(Staff branches);
    String updateStaff(Staff branches);
    String deleteStaff(Long staff_id);
    Staff getStaffDetails(Long staff_id);
    List<Staff> getAllStaffs();
}
