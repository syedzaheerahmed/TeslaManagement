package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.service.BranchService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Branch")
public class BranchController {
    BranchService BranchService;
    public BranchController(BranchService BranchService) {
        this.BranchService = BranchService;
    }

    //Get the given branch
    @GetMapping("/getBranchDetails/{branch_id}")
    public Branch getBranchDetails(@PathVariable(value = "branch_id") Long branch_id) {
        return BranchService.getBranchDetails(branch_id);
    }

    //Get all Branch
    @GetMapping("/getAllBranchDetails")
    public List<Branch> getAllBranchDetails() {
        return BranchService.getAllBranch();
    }

    //Add branch
    @PostMapping("/addBranchDetails")
    public String addBranchDetails(@RequestBody Branch Branch) {
        return BranchService.createBranch(Branch);
    }

    //update branch
    @PutMapping("/updateBranchDetails")
    public String updateBranchDetails(@RequestBody Branch Branch) {
        return BranchService.updateBranch(Branch);
    }

    //delete branch
    @DeleteMapping("/deleteBranch/{branch_id}")
    public String deleteBranchDetails(@PathVariable(value = "branch_id") Long branch_id) {
        return BranchService.deleteBranch(branch_id);
    }
}
