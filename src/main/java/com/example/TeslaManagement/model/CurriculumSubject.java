//package com.example.TeslaManagement.model;
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "curriculum_subjects",
//        indexes = @Index(columnList = "curriculum_id, order_index"))
//@Getter @Setter @NoArgsConstructor
//public class CurriculumSubject {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "curriculum_id", nullable = false, foreignKey = @ForeignKey(name = "fk_currsub_curriculum"))
//    private Curriculum curriculum;
//
//    @ManyToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "subject_id", nullable = false, foreignKey = @ForeignKey(name = "fk_currsub_subject"))
//    private Subject subject;
//
//    @Column(name = "order_index")
//    private Integer orderIndex;
//
//    @Column(name = "max_marks")
//    private Integer maxMarks;
//
//    @Column(name = "pass_marks")
//    private Integer passMarks;
//
//    @Column(nullable = false)
//    private boolean mandatory = true;
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
//}
