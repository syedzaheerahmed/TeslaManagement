package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.StaffDTO;
import com.example.TeslaManagement.DTO.StaffRequestDTO;
import com.example.TeslaManagement.DTO.StaffWithUserResponseDTO;
import com.example.TeslaManagement.model.User;

import java.util.List;

public interface StaffService {
    StaffDTO createStaff(StaffRequestDTO staffRequestDTO);
    List<StaffDTO> getAllStaff();
    List<StaffDTO> getStaffById(Long id);
    StaffDTO updateStaff(Long id, StaffRequestDTO staffRequestDTO);
    void deleteStaff(Long id);
    List<StaffDTO> getStaffByBranchId(Long branchId);
    StaffWithUserResponseDTO createStaffWithUser(StaffRequestDTO request, User requestor);
}
