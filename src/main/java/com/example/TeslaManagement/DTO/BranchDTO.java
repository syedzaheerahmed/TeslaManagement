package com.example.TeslaManagement.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {
    private Long branchId;
    private String branchAddress;
    private String branchName;
    private Long hqId;
    private String hqName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
