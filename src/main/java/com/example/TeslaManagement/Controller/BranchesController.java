package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.model.Branches;
import com.example.TeslaManagement.service.BranchesService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchesController {
    BranchesService branchesService;
    public BranchesController(BranchesService branchesService) {
        this.branchesService = branchesService;
    }

    //Get the given branch
    @GetMapping("/getBranchDetails/{branch_id}")
    public Branches getBranchDetails(@PathVariable(value = "branch_id") Long branch_id) {
        return branchesService.getBranchDetails(branch_id);
    }

    //Get all branches
    @GetMapping("/getAllBranchDetails")
    public List<Branches> getAllBranchDetails() {
        return branchesService.getAllBranches();
    }

    //Add branch
    @PostMapping("/addBranchDetails")
    public String addBranchDetails(@RequestBody Branches branches) {
        return branchesService.createBranches(branches);
    }

    //update branch
    @PutMapping("/updateBranchDetails")
    public String updateBranchDetails(@RequestBody Branches branches) {
        return branchesService.updateBranches(branches);
    }

    //delete branch
    @DeleteMapping("/deleteBranch/{branch_id}")
    public String deleteBranchDetails(@PathVariable(value = "branch_id") Long branch_id) {
        return branchesService.deleteBranches(branch_id);
    }
}
