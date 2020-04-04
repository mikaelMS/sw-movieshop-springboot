package com.oth.sw.mikesmovieshop.mikesmovieshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class MySecurityUtilities {
    // TODO: move hash
    //@Value("#{environment.USER_PASSWORD_SALT}")
    //@Value("${application-config.user-password-salt}")
    private static String salt = "Super-streng-geh€1m";
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(15, new SecureRandom(salt.getBytes()));
    }
}