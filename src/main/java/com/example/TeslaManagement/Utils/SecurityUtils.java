package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtils {

    @Autowired
    private UserRepo userRepo;

    /**
     * Get the currently authenticated user
     * @return Current authenticated user
     * @throws ResourceNotFoundException if user not found
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("No authenticated user found");
        }

        String username = authentication.getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found."));
    }

    /**
     * Get the currently authenticated username
     * @return Current username
     */
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ResourceNotFoundException("No authenticated user found");
        }
        return authentication.getName();
    }

    /**
     * Check if user has a specific role
     * @param role Role to check
     * @return true if user has the role
     */
    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }
}