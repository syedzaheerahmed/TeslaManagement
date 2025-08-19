package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface EnrollmentsRepo extends JpaRepository<Enrollment, Long> {

    // Find enrollments by class ID
    List<Enrollment> findByClassId(Long classId);

    // Find enrollments by student ID
    List<Enrollment> findByStudentId(Long studentId);

    // Find specific enrollment by class and student
    Optional<Enrollment> findByClassIdAndStudentId(Long classId, Long studentId);

    // Check if student is enrolled in a class
    boolean existsByClassIdAndStudentId(Long classId, Long studentId);

    // Find students enrolled in classes of a specific branch
    @Query("SELECT e FROM Enrollments e " +
            "JOIN Classes c ON e.classId = c.classId " +
            "WHERE c.branchId = :branchId")
    List<Enrollment> findEnrollmentsByBranch(@Param("branchId") Long branchId);

    // Find students enrolled in classes taught by specific staff
    @Query("SELECT e FROM Enrollments e " +
            "JOIN Classes c ON e.classId = c.classId " +
            "WHERE c.staffId = :staffId")
    List<Enrollment> findEnrollmentsByStaff(@Param("staffId") Long staffId);

    // Find active students enrolled in a class (students who are active and fees paid)
    @Query("SELECT e FROM Enrollments e " +
            "JOIN Students s ON e.studentId = s.studentId " +
            "WHERE e.classId = :classId AND s.isActive = true AND s.isFeesPaid = true")
    List<Enrollment> findActiveStudentsInClass(@Param("classId") Long classId);

    // Count enrollments by class
    long countByClassId(Long classId);

    // Count enrollments by student
    long countByStudentId(Long studentId);

    // Find enrollments with student details for attendance marking
    @Query("SELECT e, s FROM Enrollments e " +
            "JOIN Students s ON e.studentId = s.studentId " +
            "WHERE e.classId = :classId AND s.isActive = true")
    List<Object[]> findEnrollmentsWithStudentDetails(@Param("classId") Long classId);

    // Find recent enrollments (within last N days)
    @Query("SELECT e FROM Enrollments e WHERE e.enrollmentDate >= :fromDate")
    List<Enrollment> findRecentEnrollments(@Param("fromDate") LocalDate fromDate);

    // Find enrollments by date range
    List<Enrollment> findByEnrollmentDateBetween(LocalDate startDate, LocalDate endDate);

    // Find student enrollments in same branch classes
    @Query("SELECT e FROM Enrollments e " +
            "JOIN Classes c ON e.classId = c.classId " +
            "JOIN Students s ON e.studentId = s.studentId " +
            "WHERE s.branchId = c.branchId AND s.studentId = :studentId")
    List<Enrollment> findValidEnrollmentsForStudent(@Param("studentId") Long studentId);

    // Find enrollments for attendance tracking (with class and student info)
    @Query("SELECT e, c, s FROM Enrollments e " +
            "JOIN Classes c ON e.classId = c.classId " +
            "JOIN Students s ON e.studentId = s.studentId " +
            "WHERE c.branchId = :branchId AND c.staffId = :staffId " +
            "AND s.isActive = true AND s.isFeesPaid = true")
    List<Object[]> findEnrollmentsForAttendance(@Param("branchId") Long branchId,
                                                @Param("staffId") Long staffId);
}