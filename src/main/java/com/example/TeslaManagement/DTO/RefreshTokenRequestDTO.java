package com.example.TeslaManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequestDTO {

    @NotBlank(message = "Refresh token is required")
    private String token;
}
