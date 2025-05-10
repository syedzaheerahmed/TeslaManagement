package com.example.TeslaManagement.config;

import com.example.TeslaManagement.CustomException.TokenExpiredException;
import com.example.TeslaManagement.Utils.UserLoadImpl;
import com.example.TeslaManagement.service.impl.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;

    @Autowired
    UserLoadImpl userLoadImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userLoadImpl.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }
        catch (TokenExpiredException | com.example.TeslaManagement.CustomException.InvalidTokenException e) {
            logger.warn("JWT validation failed: {}", e);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"status\": 401, \"error\": \"" + e.getClass().getSimpleName() + "\", \"message\": \"" + e.getMessage() + "\", \"details\": \"" + e.getMessage() + "\", \"timestamp\": \"" + LocalDateTime.now() + "\"}"
            );
            return;
        } catch (Exception e) {
            logger.error("Unexpected error in JWT filter: {}", e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"status\": 500, \"error\": \"SERVER_ERROR\", \"message\": \"An unexpected error occurred\", \"details\": \"Please try again later\", \"timestamp\": \"" + LocalDateTime.now() + "\"}"
            );
            return;
        }
    }

}