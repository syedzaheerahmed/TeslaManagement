package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Branch;

import java.util.List;

public interface BranchService {
    String createBranch(Branch Branch);
    String updateBranch(Branch Branch);
    String deleteBranch(Long branch_id);
    Branch getBranchDetails(Long branch_id);
    List<Branch> getAllBranch();

}
