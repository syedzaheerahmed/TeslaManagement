package com.example.TeslaManagement.service.impl;

import com.example.TeslaManagement.CustomException.ResourceNotFoundException;
import com.example.TeslaManagement.CustomException.TokenExpiredException;
import com.example.TeslaManagement.model.RefreshToken;
import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.repository.RefreshTokenRepo;
import com.example.TeslaManagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {

    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

    @Autowired
    RefreshTokenRepo refreshTokenRepo;

    @Autowired
    UserRepo userRepository;

    @Value("${refresh-token.expiry}")
    private long refreshTokenExpiry;

    public RefreshToken createRefreshToken(String username) {
        logger.debug("Creating refresh token for username: {}", username);
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));

        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenExpiry)) // 10 minutes
                .build();
        RefreshToken savedToken = refreshTokenRepo.save(refreshToken);
        logger.info("Refresh token created for username: {}", username);
        return savedToken;
    }



    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepo.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepo.delete(token);
            logger.warn("Refresh token expired: {}", token.getToken());
            throw new TokenExpiredException("Refresh token has expired. Please log in again.");
        }
        return token;
    }

}
