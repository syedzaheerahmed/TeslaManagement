package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StudentRepo extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s JOIN FETCH s.branch b WHERE b.branchId = :branchId")
    List<Student> findByBranch(@Param("branchId") Long branchId);

    /**
     * Find students by branch ID and active status
     */
    List<Student> findByBranchBranchIdAndIsActiveTrue(Long branchId);

    /**
     * Find all students by branch ID
     */
    List<Student> findByBranchBranchId(Long branchId);

    /**
     * Find students by branch ID with pagination
     */
    Page<Student> findByBranchBranchId(Long branchId, Pageable pageable);

    /**
     * Find active students by branch ID with pagination
     */
    Page<Student> findByBranchBranchIdAndIsActiveTrue(Long branchId, Pageable pageable);

    /**
     * Find approved students by branch ID
     */
    List<Student> findByBranchBranchIdAndIsApprovedTrue(Long branchId);

    /**
     * Find student by user ID (created_by)
     */
    Optional<Student> findByCreatedByUserId(Long userId);

    /**
     * Check if student exists with given user ID
     */
    boolean existsByCreatedByUserId(Long userId);

    /**
     * Find students by name containing (case insensitive search)
     */
    @Query("SELECT s FROM Student s WHERE LOWER(s.studentName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Student> findByStudentNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find students by parent contact
     */
    List<Student> findByParentContact(String parentContact);

    /**
     * Count active students by branch
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE s.branch.branchId = :branchId AND s.isActive = true")
    Long countActiveStudentsByBranch(@Param("branchId") Long branchId);

    /**
     * Count approved students by branch
     */
    @Query("SELECT COUNT(s) FROM Student s WHERE s.branch.branchId = :branchId AND s.isApproved = true")
    Long countApprovedStudentsByBranch(@Param("branchId") Long branchId);

    /**
     * Find students by batch year and branch
     */
    List<Student> findByBatchYearAndBranchBranchId(Integer batchYear, Long branchId);
}
