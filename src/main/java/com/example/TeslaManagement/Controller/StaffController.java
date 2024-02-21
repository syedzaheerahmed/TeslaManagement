package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Staff;
import com.example.TeslaManagement.service.StaffService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    StaffService staffService;
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    //Get the given Staff details
    @GetMapping("/getStaffDetails/{staff_id}")
    public Staff getStaffDetails(@PathVariable(value = "staff_id") Long staff_id) {
        return staffService.getStaffDetails(staff_id);
    }

    //Get all staff details
    @GetMapping("/getAllStaffDetails")
    public List<Staff> getAllStaffDetails() {
        return staffService.getAllStaffs();
    }

    //Add staff
    @PostMapping("/addStaffDetails")
    public String addStaffDetails(@RequestBody Staff staff) {
        return staffService.createStaff(staff);
    }

    //update staff
    @PutMapping("/updateStaffDetails")
    public String updateStaffDetails(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    //delete staff
    @DeleteMapping("/deleteStaff/{staff_id}")
    public String deleteStaffDetails(@PathVariable(value = "staff_id") Long staff_id) {
        return staffService.deleteStaff(staff_id);
    }
}
