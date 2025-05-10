package com.example.TeslaManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "classes")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="class_id")
    private Long classId;

    @Column(name="batch_name", nullable = false)
    private String batchName;

    @Column(name="batch_timings", nullable = false)
    private String batchTimings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id", nullable = false)
    private Staff staff;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
