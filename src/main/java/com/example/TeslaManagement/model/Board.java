package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "boards",
        uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
        indexes = @Index(columnList = "name"))
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;       // "Central Board of Secondary Education"

    @Column(nullable = false, length = 32)
    private String code;       // "CBSE", "TN_STATE", etc.

    @Column(length = 512)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
//
//    @Version
//    private Long version;
}

