package com.example.TeslaManagement.repository;

import com.example.TeslaManagement.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface BranchRepo extends JpaRepository<Branch, Long> {

    @Query("SELECT b FROM Branch b JOIN FETCH b.hq WHERE b.branchId = :id")
    Optional<Branch> findByIdWithHq(@Param("id") Long id);

    @Query("SELECT b FROM Branch b JOIN FETCH b.hq")
    List<Branch> findAllWithHq();

    @Query("SELECT b FROM Branch b WHERE b.branchName = :branchName")
    List<Branch> findByBranchName(@Param("branchName") String branchName);

    @Query("SELECT ur.branch FROM UserRole ur WHERE ur.user.userId = :userid")
    Optional<Branch> findBranchesByUserId(@Param("userid") Long userid);
}
