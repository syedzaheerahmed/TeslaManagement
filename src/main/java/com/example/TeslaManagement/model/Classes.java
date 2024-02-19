package com.example.TeslaManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long class_id;
    private String batch_name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="branch_id", foreignKey = @ForeignKey(name = "fk_branch_id"), referencedColumnName = "branch_id")
    private Branches branches;
    private String batch_timings;
    private long staff_id;
}
