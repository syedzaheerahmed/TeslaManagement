package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StudentAttendanceRepo extends JpaRepository<StudentAttendance, Long> {

//    // Find attendance by class session
//    List<StudentAttendance> findByClassSessionId(Long classSessionId);
//
//    // Find attendance by student
//    List<StudentAttendance> findByStudentId(Long studentId);
//
//    // Find specific attendance record
//    Optional<StudentAttendance> findByClassSessionIdAndStudentId(Long classSessionId, Long studentId);
//
//    // Check if attendance exists for student in session
//    boolean existsByClassSessionIdAndStudentId(Long classSessionId, Long studentId);
//
//    // Find attendance by student and date range
//    @Query("SELECT sa FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE sa.studentId = :studentId " +
//            "AND cs.sessionDate BETWEEN :startDate AND :endDate")
//    List<StudentAttendance> findAttendanceByStudentAndDateRange(@Param("studentId") Long studentId,
//                                                                @Param("startDate") LocalDate startDate,
//                                                                @Param("endDate") LocalDate endDate);
//
//    // Find attendance by class and date range
//    @Query("SELECT sa FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE cs.classId = :classId " +
//            "AND cs.sessionDate BETWEEN :startDate AND :endDate")
//    List<StudentAttendance> findAttendanceByClassAndDateRange(@Param("classId") Long classId,
//                                                              @Param("startDate") LocalDate startDate,
//                                                              @Param("endDate") LocalDate endDate);
//
//    // Find attendance by branch and date range
//    @Query("SELECT sa FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "WHERE c.branchId = :branchId " +
//            "AND cs.sessionDate BETWEEN :startDate AND :endDate")
//    List<StudentAttendance> findAttendanceByBranchAndDateRange(@Param("branchId") Long branchId,
//                                                               @Param("startDate") LocalDate startDate,
//                                                               @Param("endDate") LocalDate endDate);
//
//    // Calculate attendance percentage for student in a class
//    @Query("SELECT (CAST(COUNT(CASE WHEN sa.isPresent = true THEN 1 END) AS DOUBLE) / COUNT(*)) * 100 " +
//            "FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE sa.studentId = :studentId AND cs.classId = :classId")
//    Optional<Double> calculateAttendancePercentage(@Param("studentId") Long studentId,
//                                                   @Param("classId") Long classId);
//
//    // Find students with low attendance (below threshold)
//    @Query("SELECT sa.studentId, " +
//            "(CAST(COUNT(CASE WHEN sa.isPresent = true THEN 1 END) AS DOUBLE) / COUNT(*)) * 100 as percentage " +
//            "FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE cs.classId = :classId " +
//            "GROUP BY sa.studentId " +
//            "HAVING percentage < :threshold")
//    List<Object[]> findStudentsWithLowAttendance(@Param("classId") Long classId,
//                                                 @Param("threshold") Double threshold);
//
//    // Count present students in a session
//    @Query("SELECT COUNT(sa) FROM StudentAttendance sa " +
//            "WHERE sa.classSessionId = :classSessionId AND sa.isPresent = true")
//    long countPresentStudents(@Param("classSessionId") Long classSessionId);
//
//    // Count absent students in a session
//    @Query("SELECT COUNT(sa) FROM StudentAttendance sa " +
//            "WHERE sa.classSessionId = :classSessionId AND sa.isPresent = false")
//    long countAbsentStudents(@Param("classSessionId") Long classSessionId);
//
//    // Find attendance with student and session details
//    @Query("SELECT sa, s, cs FROM StudentAttendance sa " +
//            "JOIN Students s ON sa.studentId = s.studentId " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE cs.classId = :classId " +
//            "AND cs.sessionDate BETWEEN :startDate AND :endDate")
//    List<Object[]> findAttendanceWithDetails(@Param("classId") Long classId,
//                                             @Param("startDate") LocalDate startDate,
//                                             @Param("endDate") LocalDate endDate);
//
//    // Find attendance summary by class
//    @Query("SELECT cs.sessionDate, COUNT(sa) as totalStudents, " +
//            "COUNT(CASE WHEN sa.isPresent = true THEN 1 END) as presentCount, " +
//            "COUNT(CASE WHEN sa.isPresent = false THEN 1 END) as absentCount " +
//            "FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "WHERE cs.classId = :classId " +
//            "GROUP BY cs.sessionDate " +
//            "ORDER BY cs.sessionDate DESC")
//    List<Object[]> findAttendanceSummaryByClass(@Param("classId") Long classId);
//
//    // Find monthly attendance summary
//    @Query("SELECT EXTRACT(YEAR FROM cs.sessionDate) as year, " +
//            "EXTRACT(MONTH FROM cs.sessionDate) as month, " +
//            "COUNT(sa) as totalRecords, " +
//            "COUNT(CASE WHEN sa.isPresent = true THEN 1 END) as presentCount " +
//            "FROM StudentAttendance sa " +
//            "JOIN ClassSessions cs ON sa.classSessionId = cs.classSessionId " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "WHERE c.branchId = :branchId " +
//            "GROUP BY EXTRACT(YEAR FROM cs.sessionDate), EXTRACT(MONTH FROM cs.sessionDate) " +
//            "ORDER BY year DESC, month DESC")
//    List<Object[]> findMonthlyAttendanceSummary(@Param("branchId") Long branchId);
//
//    // Find student attendance streak (consecutive present days)
//    @Query(value = "WITH attendance_dates AS (" +
//            "SELECT sa.student_id, cs.session_date, sa.is_present, " +
//            "ROW_NUMBER() OVER (PARTITION BY sa.student_id ORDER BY cs.session_date) - " +
//            "ROW_NUMBER() OVER (PARTITION BY sa.student_id, sa.is_present ORDER BY cs.session_date) as grp " +
//            "FROM student_attendance sa " +
//            "JOIN class_sessions cs ON sa.class_session_id = cs.class_session_id " +
//            "WHERE sa.student_id = :studentId AND sa.is_present = true" +
//            ") " +
//            "SELECT COUNT(*) as streak_length " +
//            "FROM attendance_dates " +
//            "WHERE grp = (SELECT grp FROM attendance_dates ORDER BY session_date DESC LIMIT 1)",
//            nativeQuery = true)
//    Optional<Integer> findCurrentAttendanceStreak(@Param("studentId") Long studentId);
}
