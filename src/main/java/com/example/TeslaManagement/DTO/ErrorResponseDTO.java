package com.example.TeslaManagement.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String error;
    private String message;
    private String details;
    private LocalDateTime timestamp;
}