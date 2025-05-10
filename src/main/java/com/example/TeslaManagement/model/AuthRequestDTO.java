package com.example.TeslaManagement.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequestDTO {
    private String username;
    private String password;
}
