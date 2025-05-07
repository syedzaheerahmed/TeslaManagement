package com.example.TeslaManagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="is_active", nullable = false)
    private Boolean isActive= true;

    @Column(name="modified_at", nullable = false)
    private LocalDateTime  modifiedAt;

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password; //TODO: store hashed passwords in prod

    @Column(name="username", unique = true, nullable = false)
    private String username;

}