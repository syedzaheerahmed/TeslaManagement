package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.CustomException.InvalidInputException;
import com.example.TeslaManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserIdGeneratorUtils {

    @Autowired
    private UserRepo userRepo;

    // Thread-safe lock for concurrent user creation
    private final ReentrantLock lock = new ReentrantLock();

    // Role mappings
    public enum UserRole {
        STUDENT("S"),
        FACULTY("F"),
        ADMIN("A"),
        SUPER_ADMIN("SA");

        private final String code;

        UserRole(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public static UserRole fromRoleId(Long roleId) {
            switch (roleId.intValue()) {
                case 5: return STUDENT;
                case 4: return FACULTY;
                case 3: return ADMIN;
                case 2: return SUPER_ADMIN;
                default: throw new InvalidInputException("Invalid role ID: " + roleId);
            }
        }

        public static UserRole fromRoleName(String roleName) {
            switch (roleName.toLowerCase()) {
                case "student": return STUDENT;
                case "faculty": return FACULTY;
                case "admin": return ADMIN;
                case "super admin": return SUPER_ADMIN;
                default: throw new InvalidInputException("Invalid role name: " + roleName);
            }
        }
    }

    /**
     * Generates a unique user ID based on role and branch
     * Pattern: {YY}T{ROLE}{BRANCH_ID}{SERIAL}
     * Example: 25TS101, 25TF201, 25TA301, 25SA001
     *
     * @param roleId The role ID from database
     * @param branchId The branch ID (null for Super Admin)
     * @return Generated unique user ID
     */
    @Transactional
    public String generateUserId(Long roleId, Long branchId) {
        lock.lock();
        try {
            // Validate inputs
            validateInputs(roleId, branchId);

            UserRole role = UserRole.fromRoleId(roleId);
            String currentYear = String.valueOf(LocalDate.now().getYear()).substring(2); // Get last 2 digits

            // Build base pattern
            String basePattern = buildBasePattern(currentYear, role, branchId);

            // Get next serial number
            int nextSerial = getNextSerialNumber(basePattern, role, branchId);

            // Format serial number with leading zeros
            String formattedSerial = String.format("%02d", nextSerial);

            // Construct final user ID
            String userId = basePattern + formattedSerial;

            // Double-check uniqueness (safety measure)
            if (userRepo.findByUsername(userId).isPresent()) {
                throw new InvalidInputException("Generated user ID already exists: " + userId);
            }

            return userId;

        } finally {
            lock.unlock();
        }
    }

    /**
     * Validates input parameters
     */
    private void validateInputs(Long roleId, Long branchId) {
        if (roleId == null) {
            throw new InvalidInputException("Role ID cannot be null");
        }

        UserRole role = UserRole.fromRoleId(roleId);

        // Super Admin doesn't need branch ID
        if (role == UserRole.SUPER_ADMIN && branchId != null) {
            throw new InvalidInputException("Super Admin should not have a branch ID");
        }

        // Other roles require branch ID
        if (role != UserRole.SUPER_ADMIN && branchId == null) {
            throw new InvalidInputException("Branch ID is required for role: " + role);
        }

        // Validate branch ID range (assuming single digit)
        if (branchId != null && (branchId < 1 || branchId > 9)) {
            throw new InvalidInputException("Branch ID must be between 1 and 9");
        }
    }

    /**
     * Builds the base pattern without serial number
     */
    private String buildBasePattern(String year, UserRole role, Long branchId) {
        StringBuilder pattern = new StringBuilder();
        pattern.append(year);

        // Add 'T' prefix for all roles except Super Admin
        if (role != UserRole.SUPER_ADMIN) {
            pattern.append("T");
        }

        pattern.append(role.getCode());

        // Add branch ID (formatted as single digit, or multiple digits if needed)
        if (branchId != null) {
            pattern.append(branchId);
        } else {
            // For Super Admin, use "00" as placeholder
            pattern.append("00");
        }

        return pattern.toString();
    }

    /**
     * Gets the next available serial number for the given pattern
     */
    private int getNextSerialNumber(String basePattern, UserRole role, Long branchId) {
        // Create regex pattern to match existing user IDs
        String regexPattern = "^" + Pattern.quote(basePattern) + "(\\d{2})$";
        Pattern pattern = Pattern.compile(regexPattern);

        // Get all usernames that match the pattern
        List<String> existingUsernames = userRepo.findUsernamesByPattern(basePattern + "%");

        int maxSerial = 0;

        for (String username : existingUsernames) {
            Matcher matcher = pattern.matcher(username);
            if (matcher.matches()) {
                try {
                    int serial = Integer.parseInt(matcher.group(1));
                    maxSerial = Math.max(maxSerial, serial);
                } catch (NumberFormatException e) {
                    // Skip invalid serials
                    continue;
                }
            }
        }

        // Return next serial number (starting from 1 if no existing records)
        return maxSerial + 1;
    }

    /**
     * Utility method to parse existing user ID and extract components
     * Useful for validation and debugging
     */
    public UserIdComponents parseUserId(String userId) {
        if (userId == null || userId.length() < 6) {
            throw new InvalidInputException("Invalid user ID format: " + userId);
        }

        try {
            // Extract year (first 2 digits)
            String year = userId.substring(0, 2);

            // Determine if it has 'T' prefix
            boolean hasTPrefix = userId.charAt(2) == 'T';
            int roleStartIndex = hasTPrefix ? 3 : 2;

            // Extract role and other components based on pattern
            UserRole role;
            Long branchId;
            int serialNumber;

            if (!hasTPrefix) {
                // Super Admin pattern: 25SA001
                role = UserRole.SUPER_ADMIN;
                branchId = null;
                serialNumber = Integer.parseInt(userId.substring(4));
            } else {
                // Other roles pattern: 25TS101, 25TF201, etc.
                String roleCode = userId.substring(roleStartIndex, roleStartIndex + 1);
                role = getRoleFromCode(roleCode);

                // Extract branch ID and serial
                String remaining = userId.substring(roleStartIndex + 1);
                if (remaining.length() >= 3) {
                    branchId = Long.parseLong(remaining.substring(0, 1));
                    serialNumber = Integer.parseInt(remaining.substring(1));
                } else {
                    throw new InvalidInputException("Invalid user ID format: " + userId);
                }
            }

            return new UserIdComponents(year, role, branchId, serialNumber, hasTPrefix);

        } catch (Exception e) {
            throw new InvalidInputException("Failed to parse user ID: " + userId + ". Error: " + e.getMessage());
        }
    }

    /**
     * Helper method to get role from single character code
     */
    private UserRole getRoleFromCode(String code) {
        for (UserRole role : UserRole.values()) {
            if (role.getCode().equals(code)) {
                return role;
            }
        }
        throw new InvalidInputException("Invalid role code: " + code);
    }

    /**
     * Data class to hold parsed user ID components
     */
    public static class UserIdComponents {
        private final String year;
        private final UserRole role;
        private final Long branchId;
        private final int serialNumber;
        private final boolean hasTPrefix;

        public UserIdComponents(String year, UserRole role, Long branchId, int serialNumber, boolean hasTPrefix) {
            this.year = year;
            this.role = role;
            this.branchId = branchId;
            this.serialNumber = serialNumber;
            this.hasTPrefix = hasTPrefix;
        }

        // Getters
        public String getYear() { return year; }
        public UserRole getRole() { return role; }
        public Long getBranchId() { return branchId; }
        public int getSerialNumber() { return serialNumber; }
        public boolean hasTPrefix() { return hasTPrefix; }

        @Override
        public String toString() {
            return String.format("UserIdComponents{year='%s', role=%s, branchId=%s, serialNumber=%d, hasTPrefix=%s}",
                    year, role, branchId, serialNumber, hasTPrefix);
        }
    }

    /**
     * Utility method to validate if a user ID follows the expected pattern
     */
    public boolean isValidUserIdFormat(String userId) {
        try {
            parseUserId(userId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the next available user ID for preview (without saving)
     */
    public String previewNextUserId(Long roleId, Long branchId) {
        return generateUserId(roleId, branchId);
    }
}
