package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            logger.info("Trying to connect : " + username);

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> {
                        logger.error("User not found : " + username);
                        return new UsernameNotFoundException("User not found");
                    });

            logger.info(" Found user is : " + user.getUsername());
            logger.info(" hashed password is : " + user.getPassword());
            logger.info(" role of the user is : " + user.getRole());

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
            logger.info(" Created authority is : " + authority.getAuthority());

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    List.of(authority)
            );
        } catch (Exception e) {
            logger.error("Error in loadUserByUsername: ", e);
            throw e;
        }
    }
}
