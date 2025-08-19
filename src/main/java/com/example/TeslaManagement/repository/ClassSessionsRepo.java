package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ClassSessionsRepo extends JpaRepository<ClassSession, Long> {

//    // Find sessions by class ID
//    List<ClassSession> findByClassId(Long classId);
//
//    // Find sessions by staff ID
//    List<ClassSession> findByStaffId(Long staffId);
//
//    // Find sessions by date
//    List<ClassSession> findBySessionDate(LocalDate sessionDate);
//
//    // Find sessions by date range
//    List<ClassSession> findBySessionDateBetween(LocalDate startDate, LocalDate endDate);
//
//    // Find sessions by class and date
//    Optional<ClassSession> findByClassIdAndSessionDate(Long classId, LocalDate sessionDate);
//
//    // Check if session exists for class on specific date
//    boolean existsByClassIdAndSessionDate(Long classId, LocalDate sessionDate);
//
//    // Find sessions by branch (through class relationship)
//    @Query("SELECT cs FROM ClassSessions cs " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "WHERE c.branchId = :branchId")
//    List<ClassSession> findSessionsByBranch(@Param("branchId") Long branchId);
//
//    // Find sessions by branch and date
//    @Query("SELECT cs FROM ClassSessions cs " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "WHERE c.branchId = :branchId AND cs.sessionDate = :sessionDate")
//    List<ClassSession> findSessionsByBranchAndDate(@Param("branchId") Long branchId,
//                                                    @Param("sessionDate") LocalDate sessionDate);
//
//    // Find sessions by staff and date
//    List<ClassSession> findByStaffIdAndSessionDate(Long staffId, LocalDate sessionDate);
//
//    // Find sessions by branch, staff and date range
//    @Query("SELECT cs FROM ClassSessions cs " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "WHERE c.branchId = :branchId AND cs.staffId = :staffId " +
//            "AND cs.sessionDate BETWEEN :startDate AND :endDate")
//    List<ClassSession> findSessionsByBranchStaffAndDateRange(@Param("branchId") Long branchId,
//                                                              @Param("staffId") Long staffId,
//                                                              @Param("startDate") LocalDate startDate,
//                                                              @Param("endDate") LocalDate endDate);
//
//    // Find sessions with attendance count
//    @Query("SELECT cs, COUNT(sa) as attendanceCount FROM ClassSessions cs " +
//            "LEFT JOIN StudentAttendance sa ON cs.classSessionId = sa.classSessionId " +
//            "WHERE cs.classId = :classId " +
//            "GROUP BY cs")
//    List<Object[]> findSessionsWithAttendanceCount(@Param("classId") Long classId);
//
//    // Find sessions for attendance marking (sessions without complete attendance)
//    @Query("SELECT cs FROM ClassSessions cs " +
//            "WHERE cs.classId = :classId AND cs.sessionDate = :sessionDate " +
//            "AND NOT EXISTS (SELECT 1 FROM StudentAttendance sa " +
//            "JOIN Enrollments e ON sa.studentId = e.studentId " +
//            "WHERE sa.classSessionId = cs.classSessionId AND e.classId = :classId)")
//    List<ClassSession> findSessionsForAttendanceMarking(@Param("classId") Long classId,
//                                                         @Param("sessionDate") LocalDate sessionDate);
//
//    // Count sessions by class
//    long countByClassId(Long classId);
//
//    // Count sessions by staff
//    long countByStaffId(Long staffId);
//
//    // Find recent sessions (last N days)
//    @Query("SELECT cs FROM ClassSessions cs WHERE cs.sessionDate >= :fromDate")
//    List<ClassSession> findRecentSessions(@Param("fromDate") LocalDate fromDate);
//
//    // Find upcoming sessions
//    @Query("SELECT cs FROM ClassSessions cs WHERE cs.sessionDate > :currentDate")
//    List<ClassSession> findUpcomingSessions(@Param("currentDate") LocalDate currentDate);
//
//    // Find sessions with full details for attendance view
//    @Query("SELECT cs, c, s FROM ClassSessions cs " +
//            "JOIN Classes c ON cs.classId = c.classId " +
//            "JOIN Staff s ON cs.staffId = s.staffId " +
//            "WHERE c.branchId = :branchId AND cs.sessionDate = :sessionDate")
//    List<Object[]> findSessionsWithDetails(@Param("branchId") Long branchId,
//                                           @Param("sessionDate") LocalDate sessionDate);
}