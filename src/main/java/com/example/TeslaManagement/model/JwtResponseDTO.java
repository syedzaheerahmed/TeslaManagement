package com.example.TeslaManagement.model;

import lombok.*;

/**
 * Represents an authentication token.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    private String token;
}
