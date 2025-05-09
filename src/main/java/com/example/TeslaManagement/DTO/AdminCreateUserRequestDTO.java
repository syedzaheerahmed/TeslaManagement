package com.example.TeslaManagement.DTO;

public record AdminCreateUserRequestDTO(String username, String password, String roleName, Long branchId) {
}
