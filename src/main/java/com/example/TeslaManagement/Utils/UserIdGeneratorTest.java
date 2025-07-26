package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserIdGeneratorTest {

    @Autowired
    private UserIdGeneratorUtils userIdGenerator;

    public void demonstrateUsage() {
        try {
            // Test Student ID generation (Role ID: 5, Branch ID: 1)
            String studentId = userIdGenerator.generateUserId(5L, 1L);
            System.out.println("Generated Student ID: " + studentId); // Output: 25TS101

            // Test Faculty ID generation (Role ID: 3, Branch ID: 2)
            String facultyId = userIdGenerator.generateUserId(4L, 2L);
            System.out.println("Generated Faculty ID: " + facultyId); // Output: 25TF201

            // Test Admin ID generation (Role ID: 2, Branch ID: 3)
            String adminId = userIdGenerator.generateUserId(3L, 3L);
            System.out.println("Generated Admin ID: " + adminId); // Output: 25TA301

            // Test Super Admin ID generation (Role ID: 1, Branch ID: null)
            String superAdminId = userIdGenerator.generateUserId(2L, null);
            System.out.println("Generated Super Admin ID: " + superAdminId); // Output: 25SA001

            // Parse existing user ID
            UserIdGeneratorUtils.UserIdComponents components = userIdGenerator.parseUserId("25TS101");
            System.out.println("Parsed components: " + components);

            // Validate user ID format
            boolean isValid = userIdGenerator.isValidUserIdFormat("25TS101");
            System.out.println("Is valid format: " + isValid); // Output: true

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Test scenarios for different conditions
    public void testEdgeCases() {
        // Test sequential numbering
        // If 25TS188 exists, next should be 25TS189

        // Test with no existing records (should start with 01)

        // Test concurrent access (multiple threads creating users simultaneously)

        // Test invalid inputs
        try {
            userIdGenerator.generateUserId(null, 1L); // Should throw exception
        } catch (InvalidInputException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            userIdGenerator.generateUserId(5L, null); // Student without branch - should throw exception
        } catch (InvalidInputException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        try {
            userIdGenerator.generateUserId(1L, 1L); // Super Admin with branch - should throw exception
        } catch (InvalidInputException e) {
            System.out.println("Expected error: " + e.getMessage());
        }
    }
}
