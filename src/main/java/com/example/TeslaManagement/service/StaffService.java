package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Staff;
import java.util.List;

public interface StaffService {
    public String createStaff(Staff branches);
    public String updateStaff(Staff branches);
    public String deleteStaff(Long staff_id);
    public Staff getStaffDetails(Long staff_id);
    public List<Staff> getAllStaffs();
}
