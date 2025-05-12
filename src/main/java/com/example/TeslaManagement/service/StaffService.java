package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.StaffDTO;
import com.example.TeslaManagement.DTO.StaffRequestDTO;

import java.util.List;

public interface StaffService {
    StaffDTO createStaff(StaffRequestDTO staffRequestDTO);
    List<StaffDTO> getAllStaff();
    StaffDTO getStaffById(Long id);
    StaffDTO updateStaff(Long id, StaffRequestDTO staffRequestDTO);
    void deleteStaff(Long id);
}
