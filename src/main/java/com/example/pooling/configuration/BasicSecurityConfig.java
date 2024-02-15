package com.example.pooling.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BasicSecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //  @Bean
    // UserDetailsService userDetailsService() {
    //     UserDetails userDetails = User.builder().
    //             username("DURGESH")
    //             .password(passwordEncoder().encode("DURGESH")).roles("ADMIN").
    //             build();
    //     return new InMemoryUserDetailsManager(userDetails);
    // }


   
    
}