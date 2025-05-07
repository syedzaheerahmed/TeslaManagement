package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.repository.BranchRepo;
import com.example.TeslaManagement.service.BranchService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    BranchRepo BranchRepo;
    public BranchServiceImpl(BranchRepo BranchRepo) {
        this.BranchRepo = BranchRepo;
    }
    @Override
    public String createBranch(Branch Branch) {
        try{
            BranchRepo.save(Branch);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in adding branch details";
        }
        return "Branch details added successfully";
    }
    @Override
    public String updateBranch(Branch Branch) {
        try{
            BranchRepo.save(Branch);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in updating branch details";
        }
        return "Branch details updated successfully";
    }
    @Override
    public String deleteBranch(Long branch_id) {
        try{
            BranchRepo.deleteById(branch_id);
        }
        catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
            return "Error in deleting branch details";
        }
        return "Branch details deleted successfully";
    }
    @Override
    public Branch getBranchDetails(Long branch_id) {
        try {
            if(BranchRepo.findById(branch_id).isPresent()) {
                return BranchRepo.getReferenceById(branch_id);
            }
        }catch (Exception e) {
            System.out.println("Exception Occurred : "+e.getMessage());
        }
        return null;
    }
    @Override
    public List<Branch> getAllBranch() {
        return BranchRepo.findAll();
    }
}
