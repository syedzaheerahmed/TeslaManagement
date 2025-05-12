package com.example.TeslaManagement.service;

import com.example.TeslaManagement.DTO.BranchDTO;
import com.example.TeslaManagement.DTO.BranchRequestDTO;

import java.util.List;

public interface BranchService {
    List<BranchDTO> getAllBranches();
    BranchDTO getBranchById(Long id);
    BranchDTO createBranch(BranchRequestDTO branchRequestDTO);
    BranchDTO updateBranch(Long id, BranchRequestDTO branchRequestDTO);
    void deleteBranch(Long id);
}
