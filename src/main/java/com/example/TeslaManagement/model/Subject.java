package com.example.TeslaManagement.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "subjects",
        uniqueConstraints = @UniqueConstraint(columnNames = {"code"}),
        indexes = @Index(columnList = "name"))
@Getter @Setter @NoArgsConstructor
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=120)
    private String name;    // "Mathematics"

    @Column(nullable=false, length=32)
    private String code;    // "MATH", stable ref used in reports/APIs

    @Column(nullable=false)
    private boolean elective = false;

    @Column(length=512)
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

