package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.CustomException.ItemAlreadyExistsException;
import com.example.TeslaManagement.DTO.BranchDTO;
import com.example.TeslaManagement.DTO.BranchRequestDTO;
import com.example.TeslaManagement.model.Branch;
import com.example.TeslaManagement.model.HQ;
import com.example.TeslaManagement.repository.BranchRepo;
import com.example.TeslaManagement.repository.HQRepo;
import com.example.TeslaManagement.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private HQRepo hqRepo;

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepo.findAllWithHq();
        return branches.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id) {
        Branch branch = branchRepo.findByIdWithHq(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
        return convertToDTO(branch);
    }

    @Override
    public BranchDTO createBranch(BranchRequestDTO branchRequestDTO) {
        HQ hq = hqRepo.findById(branchRequestDTO.getHqId())
                .orElseThrow(() -> new ResourceNotFoundException("HQ not found with id: " + branchRequestDTO.getHqId()));
        List<Branch> isBranchExist = branchRepo.findByBranchName(branchRequestDTO.getBranchName());
        if(!isBranchExist.isEmpty()){
            throw new ItemAlreadyExistsException("Branch '" + branchRequestDTO.getBranchName() + "' already exists.");
        }
        Branch branch = new Branch();
        branch.setBranchAddress(branchRequestDTO.getBranchAddress());
        branch.setBranchName(branchRequestDTO.getBranchName());
        branch.setHq(hq);
        Branch savedBranch = branchRepo.save(branch);
        return convertToDTO(savedBranch);
    }

    @Override
    public BranchDTO updateBranch(Long id, BranchRequestDTO branchRequestDTO) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
        HQ hq = hqRepo.findById(branchRequestDTO.getHqId())
                .orElseThrow(() -> new ResourceNotFoundException("HQ not found with id: " + branchRequestDTO.getHqId()));
        branch.setBranchAddress(branchRequestDTO.getBranchAddress());
        branch.setBranchName(branchRequestDTO.getBranchName());
        branch.setHq(hq);
        Branch updatedBranch = branchRepo.save(branch);
        return convertToDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) {
        Branch branch = branchRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id: " + id));
        branchRepo.delete(branch);
    }

    private BranchDTO convertToDTO(Branch branch) {
        BranchDTO dto = new BranchDTO();
        dto.setBranchId(branch.getBranchId());
        dto.setBranchAddress(branch.getBranchAddress());
        dto.setBranchName(branch.getBranchName());
        dto.setHqId(branch.getHq().getHq_id());
        dto.setHqName(branch.getHq().getHq_name());
        dto.setCreatedAt(branch.getCreatedAt());
        dto.setUpdatedAt(branch.getUpdatedAt());
        return dto;
    }
}
