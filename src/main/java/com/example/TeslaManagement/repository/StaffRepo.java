package com.example.TeslaManagement.repository;


import com.example.TeslaManagement.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StaffRepo extends JpaRepository<Staff, Long> {
    @Query("SELECT s FROM Staff s JOIN FETCH s.branch JOIN FETCH s.user WHERE s.staffId = :id")
    Optional<Staff> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT s FROM Staff s JOIN FETCH s.branch JOIN FETCH s.user")
    List<Staff> findAllWithDetails();

//    @Query("SELECT s FROM Staff s JOIN FETCH s.branch b WHERE b.branchId = :branchId")
//    List< Staff> findStaffByBranch( @Param("branchId") Long branchId);

    /**
     * Find Staffs by branch ID and active status
     */
    List<Staff> findByBranchBranchIdAndIsActiveTrue(Long branchId);


//    /**
//     * Find staff with user account details
//     */
//    @Query("SELECT s FROM Staff s LEFT JOIN FETCH s.user u LEFT JOIN FETCH u.userRole ur LEFT JOIN FETCH ur.role LEFT JOIN FETCH s.branch WHERE s.staffId = :staffId")
//    Optional<Staff> findByIdWithUserDetails(@Param("staffId") Long staffId);
//
//    /**
//     * Find all admin staff members by branch
//     */
//    @Query("SELECT s FROM Staff s JOIN FETCH s.branch b WHERE b.branchId = :branchId AND s.isAdmin = true AND s.isActive = true")
//    List<Staff> findAdminStaffByBranch(@Param("branchId") Long branchId);
//
//    /**
//     * Check if admin already exists for a branch
//     */
//    @Query("SELECT COUNT(s) > 0 FROM Staff s WHERE s.branch.branchId = :branchId AND s.isAdmin = true AND s.isActive = true")
//    boolean existsAdminForBranch(@Param("branchId") Long branchId);
//
//    /**
//     * Find staff by user ID
//     */
//    @Query("SELECT s FROM Staff s JOIN FETCH s.branch WHERE s.user.userId = :userId")
//    Optional<Staff> findByUserId(@Param("userId") Long userId);
//
//    /**
//     * Find all staff with user accounts
//     */
//    @Query("SELECT s FROM Staff s LEFT JOIN FETCH s.user u LEFT JOIN FETCH u.userRole ur LEFT JOIN FETCH ur.role LEFT JOIN FETCH s.branch WHERE s.user IS NOT NULL")
//    List<Staff> findAllStaffWithUsers();
//
//    /**
//     * Find staff by branch and admin status
//     */
//    List<Staff> findByBranchBranchIdAndIsAdminAndIsActiveTrue(Long branchId, boolean isAdmin);
//
//    /**
//     * Count active staff by branch
//     */
//    @Query("SELECT COUNT(s) FROM Staff s WHERE s.branch.branchId = :branchId AND s.isActive = true")
//    long countActiveStaffByBranch(@Param("branchId") Long branchId);
//
//    /**
//     * Find staff by contact number (for duplicate checking)
//     */
//    Optional<Staff> findByContactNumberAndIsActiveTrue(String contactNumber);
}
