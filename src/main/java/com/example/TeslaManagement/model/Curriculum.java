//package com.example.TeslaManagement.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "curriculums",
//        uniqueConstraints = @UniqueConstraint(columnNames = {"board_id","standard_id","academic_year","medium"}),
//        indexes = @Index(columnList = "board_id,standard_id,academic_year"))
//@Getter @Setter @NoArgsConstructor
//public class Curriculum {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "board_id", nullable = false, foreignKey = @ForeignKey(name = "fk_curriculum_board"))
//    private Board board;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "standard_id", nullable = false, foreignKey = @ForeignKey(name = "fk_curriculum_standard"))
//    private Standard standard;
//
//    @Column(name = "academic_year", nullable = false, length = 9)
//    private String academicYear;   // "2025-2026"
//
//    @Column(nullable = false, length = 20)
//    private String medium;         // "English", "Tamil" (or use an enum)
//
//    @Column(length = 64)
//    private String syllabusCode;   // version code if any
//
//    @Column(length = 512)
//    private String notes;
//
//    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CurriculumSubject> subjects = new ArrayList<>();
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
//
//    @Version
//    private Long version;
//
//    // convenience
//    public void addSubject(CurriculumSubject cs) {
//        cs.setCurriculum(this);
//        subjects.add(cs);
//    }
//}
