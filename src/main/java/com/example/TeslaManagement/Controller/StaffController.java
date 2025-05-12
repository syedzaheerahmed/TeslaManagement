package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.DTO.StaffDTO;
import com.example.TeslaManagement.DTO.StaffRequestDTO;
import com.example.TeslaManagement.service.StaffService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @PostMapping
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffRequestDTO staffRequestDTO) {
        StaffDTO createdStaff = staffService.createStaff(staffRequestDTO);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAllStaff() {
        List<StaffDTO> staffList = staffService.getAllStaff();
        return ResponseEntity.ok(staffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getStaffById(@PathVariable @Positive(message = "Staff ID must be positive") Long id) {
        StaffDTO staff = staffService.getStaffById(id);
        return ResponseEntity.ok(staff);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<StaffDTO> updateStaff(@PathVariable @Positive(message = "Staff ID must be positive") Long id,
                                                @Valid @RequestBody StaffRequestDTO staffRequestDTO) {
        StaffDTO updatedStaff = staffService.updateStaff(id, staffRequestDTO);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<Void> deleteStaff(@PathVariable @Positive(message = "Staff ID must be positive") Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}