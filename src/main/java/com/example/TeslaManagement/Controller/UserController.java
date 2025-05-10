package com.example.TeslaManagement.Controller;

import com.example.TeslaManagement.CustomException.AccountDisabledException;
import com.example.TeslaManagement.CustomException.InvalidCredentialsException;
import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.DTO.*;
import com.example.TeslaManagement.model.*;
import com.example.TeslaManagement.service.UserService;
import com.example.TeslaManagement.service.impl.JwtService;
import com.example.TeslaManagement.service.impl.RefreshTokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody User userDetail) {
        User createdUser = userService.createUser(userDetail);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetail) {
        User updatedUser = userService.updateUser(id, userDetail);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/admin/create-user") // New endpoint
    @PreAuthorize("hasAnyRole('Admin', 'Super Admin')")
    public ResponseEntity<User> adminCreateUser(@RequestBody AdminCreateUserRequestDTO request) {
        User createdUser = userService.createUserByAdmin(request); // New service method
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('Super Admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('Super Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable @Positive(message = "User ID must be positive") Long id) {
        logger.debug("Fetching user with ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('ROLE_Super Admin')")
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        try {
            return ResponseEntity.ok().body("Success super admin");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public JwtResponseDTO AuthenticateAndGetToken(@Valid @RequestBody AuthRequestDTO authRequestDTO){
        logger.debug("Login attempt for username: {}", authRequestDTO.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),
                    authRequestDTO.getPassword()));
            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                if (!userDetails.isEnabled()) {
                    throw new AccountDisabledException("User account is disabled");
                }
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                logger.info("Login successful for username: {}", authRequestDTO.getUsername());
                return JwtResponseDTO.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                        .token(refreshToken.getToken()).build();

            } else {
                throw new InvalidCredentialsException("invalid user request..!!");
            }
        }
        catch (AuthenticationException e) {
            logger.warn("Authentication failed for username: {}", authRequestDTO.getUsername());
            throw new InvalidCredentialsException("Invalid username or password");
        }

    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        logger.debug("Refresh token request with token: {}", refreshTokenRequestDTO.getToken());
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.GenerateToken(user.getUsername());
                    logger.info("New access token generated for username: {}", user.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken())
                            .build();
                })
                .orElseThrow(() -> {
                    logger.warn("Refresh token not found: {}", refreshTokenRequestDTO.getToken());
                    return new ResourceNotFoundException("Refresh token not found in database");
                });
    }

}
