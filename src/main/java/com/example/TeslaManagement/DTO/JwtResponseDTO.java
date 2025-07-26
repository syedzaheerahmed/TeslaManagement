package com.example.TeslaManagement.DTO;

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
    private Long user_id;
    private Long user_role;
    private boolean isActive;
}
