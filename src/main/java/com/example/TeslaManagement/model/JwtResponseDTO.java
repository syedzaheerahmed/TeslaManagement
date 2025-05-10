package com.example.TeslaManagement.model;

import lombok.*;

/**
 * Represents an authentication token.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponseDTO {
    private String accessToken;
    private String token;
}
