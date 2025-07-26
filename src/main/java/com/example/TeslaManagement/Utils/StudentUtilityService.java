package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.model.Student;
import com.example.TeslaManagement.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility service for student-related operations
 */
@Service
public class StudentUtilityService {

    @Autowired
    private StudentRepo studentRepository;

    /**
     * Get student statistics by branch
     */
    public Map<String, Object> getStudentStatsByBranch(Long branchId) {
        Map<String, Object> stats = new HashMap<>();

        Long totalStudents = studentRepository.countActiveStudentsByBranch(branchId);
        Long approvedStudents = studentRepository.countApprovedStudentsByBranch(branchId);

        stats.put("totalActiveStudents", totalStudents);
        stats.put("approvedStudents", approvedStudents);
        stats.put("pendingApproval", totalStudents - approvedStudents);
        stats.put("branchId", branchId);

        return stats;
    }

    /**
     * Get students by batch year and branch
     */
    public List<Student> getStudentsByBatchAndBranch(Integer batchYear, Long branchId) {
        return studentRepository.findByBatchYearAndBranchBranchId(batchYear, branchId);
    }

    /**
     * Search students by name
     */
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByStudentNameContainingIgnoreCase(name);
    }

    /**
     * Validate student data integrity
     */
    public boolean validateStudentIntegrity(Student student) {
        // Check if student has corresponding user
        if (student.getCreatedBy() == null) {
            return false;
        }

        // Check if student is in valid branch
        if (student.getBranch() == null) {
            return false;
        }

        // Add more validation rules as needed
        return true;
    }

    /**
     * Generate student report data
     */
    public Map<String, Object> generateStudentReport(Long branchId) {
        Map<String, Object> report = new HashMap<>();

        // Get basic stats
        Map<String, Object> stats = getStudentStatsByBranch(branchId);
        report.put("statistics", stats);

        // Get students by status
        List<Student> activeStudents = studentRepository.findByBranchBranchIdAndIsActiveTrue(branchId);
        List<Student> approvedStudents = studentRepository.findByBranchBranchIdAndIsApprovedTrue(branchId);

        report.put("activeStudentsCount", activeStudents.size());
        report.put("approvedStudentsCount", approvedStudents.size());

        return report;
    }

    /**
     * Check if username follows the expected pattern
     */
    public boolean isValidStudentUsername(String username) {
        // Expected pattern: YYTSBranchIDSerial (e.g., 25TS101)
        if (username == null || username.length() < 6) {
            return false;
        }

        // Check if it starts with year and has TS pattern
        String pattern = "^\\d{2}TS\\d{3}$";
        return username.matches(pattern);
    }

    /**
     * Extract branch ID from student username
     */
    public Long extractBranchIdFromUsername(String username) {
        if (!isValidStudentUsername(username)) {
            return null;
        }

        try {
            // Extract branch ID from position 4 (after YYTS)
            String branchStr = username.substring(4, 5);
            return Long.parseLong(branchStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Generate next student ID preview for a branch
     */
    public String previewNextStudentId(Long branchId) {
        // This would integrate with UserIdGeneratorUtils
        // For now, return a placeholder
        String currentYear = String.valueOf(java.time.LocalDate.now().getYear()).substring(2);
        return currentYear + "TS" + branchId + "XX"; // XX will be replaced with actual serial
    }
}
