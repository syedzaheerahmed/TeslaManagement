package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Staff;
import com.example.TeslaManagement.repository.StaffRepo;
import com.example.TeslaManagement.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {

    StaffRepo staffRepo;
    public StaffServiceImpl(StaffRepo staffRepo) {this.staffRepo = staffRepo;}

    @Override
    public String createStaff(Staff staff) {
        try{
            staffRepo.save(staff);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding staff details";
        }
        return "Staff details added successfully";
    }

    @Override
    public String updateStaff(Staff staff) {
        try{
            staffRepo.save(staff);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating staff details";
        }
        return "Staff details updated successfully";    }

    @Override
    public String deleteStaff(Long staff_id) {
        try{
            staffRepo.deleteById(staff_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting staff details";
        }
        return "Staff details deleted successfully";
    }

    @Override
    public Staff getStaffDetails(Long staff_id) {
        try {
            if(staffRepo.findById(staff_id).isPresent()) {
                return staffRepo.getReferenceById(staff_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }

    @Override
    public List<Staff> getAllStaffs() {
        return staffRepo.findAll();
    }
}
