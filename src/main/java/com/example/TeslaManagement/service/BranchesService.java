package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Branches;
import java.util.List;

public interface BranchesService {
    String createBranches(Branches branches);
    String updateBranches(Branches branches);
    String deleteBranches(Long branch_id);
    Branches getBranchDetails(Long branch_id);
    List<Branches> getAllBranches();

}
