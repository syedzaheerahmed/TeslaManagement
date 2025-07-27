package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.DTO.StaffDTO;
import com.example.TeslaManagement.DTO.StaffRequestDTO;
import com.example.TeslaManagement.DTO.StaffWithUserResponseDTO;
import com.example.TeslaManagement.DTO.StudentDTO;
import com.example.TeslaManagement.Utils.SecurityUtils;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @Autowired
    private SecurityUtils securityUtils;

    @PostMapping
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<StaffDTO> createStaff(@Valid @RequestBody StaffRequestDTO staffRequestDTO) {
        StaffDTO createdStaff = staffService.createStaff(staffRequestDTO);
        return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
    }

    @PostMapping("/with-user")
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public ResponseEntity<?> createStaffWithUser(@Valid @RequestBody StaffRequestDTO request) {
        User requestor = securityUtils.getCurrentUser();
        logger.info("Creating student requested by user: {}", requestor.getUsername());
        try {
            StaffWithUserResponseDTO response = staffService.createStaffWithUser(request, requestor);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of(
                            "error", "Access Denied",
                            "message", e.getMessage(),
                            "timestamp", LocalDateTime.now()
                    ));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "error", "Entity Not Found",
                            "message", e.getMessage(),
                            "timestamp", LocalDateTime.now()
                    ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "error", "Invalid Request",
                            "message", e.getMessage(),
                            "timestamp", LocalDateTime.now()
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "error", "Internal Server Error",
                            "message", "An unexpected error occurred while creating staff with user account",
                            "timestamp", LocalDateTime.now()
                    ));
        }
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

    @GetMapping("/branch/{id}")
    public ResponseEntity<?> getStaffByBranchId(@PathVariable("id") Long branchId) {
        try {
            List<StaffDTO> staffList = staffService.getStaffByBranchId(branchId);
            if (staffList.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(" No Staff found for the given branch ID: " + branchId);
            }

            return  ResponseEntity.ok(staffList);
        }
        catch( Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
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