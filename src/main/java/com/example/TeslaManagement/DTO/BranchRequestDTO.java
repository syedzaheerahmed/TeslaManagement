package com.example.TeslaManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchRequestDTO {
    @NotBlank(message = "Branch address is required")
    private String branchAddress;

    @NotBlank(message = "Branch name is required")
    private String branchName;

    @NotNull(message = "HQ ID is required")
    private Long hqId;
}
