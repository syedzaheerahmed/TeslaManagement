package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "standards",
        uniqueConstraints = @UniqueConstraint(columnNames = {"grade","name"}),
        indexes = @Index(columnList = "level"))
@Getter
@Setter
@NoArgsConstructor
public class Standard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=64)
    private String name;     // "Grade 10" / "10"

    @Column(nullable=false)
    private Integer grade;   // canonical numeric: 1..12

    @Column(length=255)
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
