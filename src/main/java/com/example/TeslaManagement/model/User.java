package com.example.TeslaManagement.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name="is_active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isActive;

    @UpdateTimestamp
    @Column(name="modified_at", nullable = false)
    private LocalDateTime  modifiedAt;

    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password; //TODO: store hashed passwords in prod

    @Column(name="username", unique = true, nullable = false)
    private String username;

    // Add relationship with UserRole - one user can have one userRole
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private UserRole userRole;

    // Add a method to get roles (needed by CustomUserDetails)
    public List<UserRole> getRoles() {
        // Since we have a one-to-one relationship, return as a list with one element
        if (userRole != null) {
            return Collections.singletonList(userRole);
        }
        return Collections.emptyList();
    }

}