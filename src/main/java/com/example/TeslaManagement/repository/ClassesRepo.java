package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ClassesRepo extends JpaRepository<ClassEntity, Long> {

    // Find classes by branch ID
    List<ClassEntity> findByBranchBranchId(Long branchId);

    // Find classes by staff ID
    List<ClassEntity> findByStaffStaffId(Long staffId);

    // Find classes by branch and staff (for security - staff can only see their own classes in their branch)
    List<ClassEntity> findByBranchBranchIdAndStaffStaffId(Long branchId, Long staffId);

    // Find active classes by branch
    @Query("SELECT c FROM ClassEntity c WHERE c.branch.branchId = :branchId AND c.isActive = true")
    List<ClassEntity> findActiveClassesByBranch(@Param("branchId") Long branchId);

//    // Find classes by batch name pattern
//    List<ClassEntity> findByBatchNameContainingIgnoreCase(String batchName);
//
//    // Find classes by branch and batch name pattern
//    List<ClassEntity> findByBranchIdAndBatchNameContainingIgnoreCase(Long branchId, String batchName);

    // Check if class exists by batch name and branch (to avoid duplicates)
    boolean existsByClassNameAndBranchBranchId(String batchName, Long branchId);

//    // Find classes with enrollment count
//    @Query("SELECT c, COUNT(e) as enrollmentCount FROM Classes c " +
//            "LEFT JOIN Enrollments e ON c.classId = e.classId " +
//            "WHERE c.branchId = :branchId " +
//            "GROUP BY c")
//    List<Object[]> findClassesWithEnrollmentCount(@Param("branchId") Long branchId);

//    // Find classes by timings pattern
//    List<ClassEntity> findByBatchTimingsContainingIgnoreCase(String timings);
//
//    // Find classes by branch and staff with enrollment details
//    @Query("SELECT DISTINCT c FROM Classes c " +
//            "LEFT JOIN FETCH Enrollments e ON c.classId = e.classId " +
//            "WHERE c.branchId = :branchId AND c.staffId = :staffId")
//    List<ClassEntity> findByBranchIdAndStaffIdWithEnrollments(@Param("branchId") Long branchId,
//                                                          @Param("staffId") Long staffId);

//    // Count classes by branch
//    long countByBranchId(Long branchId);
//
//    // Count classes by staff
//    long countByStaffId(Long staffId);

    // Find overlapping class timings for the same branch (to avoid scheduling conflicts)
    @Query("SELECT c FROM ClassEntity c WHERE c.branch.branchId = :branchId AND c.classTimings = :timings AND c.classId != :excludeClassId")
    List<ClassEntity> findOverlappingClasses(@Param("branchId") Long branchId,
                                             @Param("timings") String timings,
                                             @Param("excludeClassId") Long excludeClassId);

//    // Find classes that have sessions on a specific date
//    @Query("SELECT DISTINCT c FROM Classes c " +
//            "JOIN ClassSessions cs ON c.classId = cs.classId " +
//            "WHERE c.branchId = :branchId AND cs.sessionDate = :sessionDate")
//    List<ClassEntity> findClassesWithSessionsOnDate(@Param("branchId") Long branchId,
//                                                @Param("sessionDate") java.time.LocalDate sessionDate);
}