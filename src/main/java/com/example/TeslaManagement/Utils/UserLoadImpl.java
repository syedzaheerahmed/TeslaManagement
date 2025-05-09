package com.example.TeslaManagement.Utils;

import com.example.TeslaManagement.model.User;
import com.example.TeslaManagement.model.UserRole;
import com.example.TeslaManagement.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserLoadImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserLoadImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Entering in loadUserByUsername Method...");
        User user = userRepository.findByUsernameWithRole(username);
        if(user == null){
            logger.error("Username not found: " + username);
            throw new UsernameNotFoundException("could not found user..!!");
        }

        // Make sure to fetch the associated UserRole
        String roleName = user.getUserRole().getRole().getRoleName();
        if (roleName == null) {
            logger.warn("User found but has no associated role: " + username);
        } else {
            logger.debug("User role found: " + roleName);
        }

        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }
}