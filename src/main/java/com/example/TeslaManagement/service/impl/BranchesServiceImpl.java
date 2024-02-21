package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Branches;
import com.example.TeslaManagement.repository.BranchesRepo;
import com.example.TeslaManagement.service.BranchesService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchesServiceImpl implements BranchesService {
    BranchesRepo branchesRepo;
    public BranchesServiceImpl(BranchesRepo branchesRepo) {
        this.branchesRepo = branchesRepo;
    }
    @Override
    public String createBranches(Branches branches) {
        try{
            branchesRepo.save(branches);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding branch details";
        }
        return "Branch details added successfully";
    }
    @Override
    public String updateBranches(Branches branches) {
        try{
            branchesRepo.save(branches);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating branch details";
        }
        return "Branch details updated successfully";
    }
    @Override
    public String deleteBranches(Long branch_id) {
        try{
            branchesRepo.deleteById(branch_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting branch details";
        }
        return "Branch details deleted successfully";
    }
    @Override
    public Branches getBranchDetails(Long branch_id) {
        try {
            if(branchesRepo.findById(branch_id).isPresent()) {
                return branchesRepo.getReferenceById(branch_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }
    @Override
    public List<Branches> getAllBranches() {
        return branchesRepo.findAll();
    }
}
