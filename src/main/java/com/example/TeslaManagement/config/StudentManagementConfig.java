package com.example.TeslaManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Configuration
public class StudentManagementConfig {

    /**
     * Configuration properties for student management
     */
    @Component
    @ConfigurationProperties(prefix = "tesla.student")
    public static class StudentProperties {

        private boolean autoApproveStudents = false;
        private int defaultPageSize = 10;
        private int maxPageSize = 100;
        private boolean allowCustomUsernames = true;
        private int passwordResetTokenExpiration = 24; // hours
        private boolean sendWelcomeEmail = false;
        private String defaultBatchYear = "2025";

        // Getters and setters
        public boolean isAutoApproveStudents() {
            return autoApproveStudents;
        }

        public void setAutoApproveStudents(boolean autoApproveStudents) {
            this.autoApproveStudents = autoApproveStudents;
        }

        public int getDefaultPageSize() {
            return defaultPageSize;
        }

        public void setDefaultPageSize(int defaultPageSize) {
            this.defaultPageSize = defaultPageSize;
        }

        public int getMaxPageSize() {
            return maxPageSize;
        }

        public void setMaxPageSize(int maxPageSize) {
            this.maxPageSize = maxPageSize;
        }

        public boolean isAllowCustomUsernames() {
            return allowCustomUsernames;
        }

        public void setAllowCustomUsernames(boolean allowCustomUsernames) {
            this.allowCustomUsernames = allowCustomUsernames;
        }

        public int getPasswordResetTokenExpiration() {
            return passwordResetTokenExpiration;
        }

        public void setPasswordResetTokenExpiration(int passwordResetTokenExpiration) {
            this.passwordResetTokenExpiration = passwordResetTokenExpiration;
        }

        public boolean isSendWelcomeEmail() {
            return sendWelcomeEmail;
        }

        public void setSendWelcomeEmail(boolean sendWelcomeEmail) {
            this.sendWelcomeEmail = sendWelcomeEmail;
        }

        public String getDefaultBatchYear() {
            return defaultBatchYear;
        }

        public void setDefaultBatchYear(String defaultBatchYear) {
            this.defaultBatchYear = defaultBatchYear;
        }
    }
}
