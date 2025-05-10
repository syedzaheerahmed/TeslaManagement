package com.example.TeslaManagement.DTO;

import com.example.TeslaManagement.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDTO {
    private Long userId;
    private String username;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<String> roles;

    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.isActive = user.isActive();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
        this.roles = user.getRoles().stream()
                .map(userRole -> userRole.getRole().getRoleName())
                .collect(Collectors.toList());
    }
}