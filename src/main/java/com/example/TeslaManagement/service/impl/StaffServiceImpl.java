package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.StaffDTO;
import com.example.TeslaManagement.DTO.StaffRequestDTO;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.repository.*;
import com.example.TeslaManagement.service.StaffService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepo staffRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public StaffDTO createStaff(StaffRequestDTO staffRequestDTO) {
        // Validate User
        User user = userRepo.findById(staffRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + staffRequestDTO.getUserId()));

        // Validate Faculty role (roleId = 4)
        UserRole userRole = user.getUserRole();
        if (userRole == null || userRole.getRole().getRoleId() != 4L) {
            throw new InvalidInputException("User must have the 'Faculty' role (roleId = 4).");
        }

        // Validate Branch
        Branch branch = branchRepo.findById(staffRequestDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + staffRequestDTO.getBranchId()));

        // Create Staff entity
        Staff staff = new Staff();
        staff.setUser(user);
        staff.setBranch(branch);
        staff.setStaffName(staffRequestDTO.getStaffName());
        staff.setAddress(staffRequestDTO.getAddress());
        staff.setContactNumber(staffRequestDTO.getContactNumber());
        staff.setTeachingStaff(staffRequestDTO.isTeachingStaff());
        staff.setActive(staffRequestDTO.isActive());
        staff.setSalaryPaid(staffRequestDTO.isSalaryPaid());
        staff.setReasonForDeactivation(staffRequestDTO.getReasonForDeactivation());

        // Save and return DTO
        Staff savedStaff = staffRepo.save(staff);
        return convertToDTO(savedStaff);
    }

    @Override
    public List<StaffDTO> getAllStaff() {
        List<Staff> staffList = staffRepo.findAllWithDetails();
        return staffList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public StaffDTO getStaffById(Long id) {
        Staff staff = staffRepo.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        return convertToDTO(staff);
    }

    @Override
    public List<StaffDTO> getStaffByBranchId(Long branchId){
        boolean branchExists = branchRepo.existsById(branchId);
        if(!branchExists) throw new EntityNotFoundException( "Branch Id doesn't exist "+branchId);
        return staffRepo.findByBranchBranchIdAndIsActiveTrue(branchId).stream()
                .map( this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public StaffDTO updateStaff(Long id, StaffRequestDTO staffRequestDTO) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));

        // Validate User
        User user = userRepo.findById(staffRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + staffRequestDTO.getUserId()));

        // Validate Faculty role (roleId = 4)
        UserRole userRole = user.getUserRole();
        if (userRole == null || userRole.getRole().getRoleId() != 4L) {
            throw new InvalidInputException("User must have the 'Faculty' role (roleId = 4).");
        }

        // Validate Branch
        Branch branch = branchRepo.findById(staffRequestDTO.getBranchId())
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + staffRequestDTO.getBranchId()));

        // Update Staff entity
        staff.setUser(user);
        staff.setBranch(branch);
        staff.setStaffName(staffRequestDTO.getStaffName());
        staff.setAddress(staffRequestDTO.getAddress());
        staff.setContactNumber(staffRequestDTO.getContactNumber());
        staff.setTeachingStaff(staffRequestDTO.isTeachingStaff());
        staff.setActive(staffRequestDTO.isActive());
        staff.setSalaryPaid(staffRequestDTO.isSalaryPaid());
        staff.setReasonForDeactivation(staffRequestDTO.getReasonForDeactivation());

        // Save and return DTO
        Staff updatedStaff = staffRepo.save(staff);
        return convertToDTO(updatedStaff);
    }

    @Override
    @PreAuthorize("hasAnyRole('Super Admin', 'Admin')")
    public void deleteStaff(Long id) {
        Staff staff = staffRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Staff not found with id: " + id));
        staff.setActive(false);
        staffRepo.save(staff);
    }

    private StaffDTO convertToDTO(Staff staff) {
        StaffDTO dto = new StaffDTO();
        dto.setStaffId(staff.getStaffId());
        dto.setStaffName(staff.getStaffName());
        dto.setAddress(staff.getAddress());
        dto.setContactNumber(staff.getContactNumber());
        dto.setActive(staff.isActive());
        dto.setTeachingStaff(staff.isTeachingStaff());
        dto.setReasonForDeactivation(staff.getReasonForDeactivation());
        dto.setBranchId(staff.getBranch().getBranchId());
        dto.setBranchName(staff.getBranch().getBranchName());
        dto.setUserId(staff.getUser().getUserId());
        dto.setUsername(staff.getUser().getUsername());
        dto.setSalaryPaid(staff.isSalaryPaid());
        dto.setCreatedAt(staff.getCreatedAt());
        dto.setUpdatedAt(staff.getUpdatedAt());
        return dto;
    }
}