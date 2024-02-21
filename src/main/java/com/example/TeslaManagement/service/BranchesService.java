package com.example.TeslaManagement.service;

import com.example.TeslaManagement.model.Branches;
import java.util.List;

public interface BranchesService {
    public String createBranches(Branches branches);
    public String updateBranches(Branches branches);
    public String deleteBranches(Long branch_id);
    public Branches getBranchDetails(Long branch_id);
    public List<Branches> getAllBranches();

}
