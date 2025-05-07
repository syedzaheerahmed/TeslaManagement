package com.example.TeslaManagement.Utils;

//import com.example.TeslaManagement.repository.UserInfoRepo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserDetailsServiceHelperImpl {
//
//    @Autowired
//    private UserInfoRepo userRepository;
//
//    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceHelperImpl.class);
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        logger.debug("Entering in loadUserByUsername Method...");
//        UserInfo user = userRepository.findByUsername(username);
//        if(user == null){
//            logger.error("Username not found: " + username);
//            throw new UsernameNotFoundException("could not found user..!!");
//        }
//        logger.info("User Authenticated Successfully..!!!");
//        return new CustomUserDetailsServiceHelper(user);
//    }
//
//}
