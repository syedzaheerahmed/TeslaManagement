package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.DTO.BranchDTO;
import com.example.TeslaManagement.DTO.BranchRequestDTO;
import com.example.TeslaManagement.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        List<BranchDTO> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        BranchDTO branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/user/{userid}")
    public ResponseEntity<List<BranchDTO>> getBranchByUserId(@PathVariable("userid") Long userid) {
        List<BranchDTO> branch = branchService.getBranchByUserId(userid);
        return ResponseEntity.ok(branch);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('Super Admin')")
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchRequestDTO branchRequestDTO) {
        BranchDTO createdBranch = branchService.createBranch(branchRequestDTO);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchRequestDTO branchRequestDTO) {
        BranchDTO updatedBranch = branchService.updateBranch(id, branchRequestDTO);
        return ResponseEntity.ok(updatedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
