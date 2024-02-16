package com.example.pooling.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pooling.model.Role;
import com.example.pooling.model.User;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role userRole = new Role(null, Role.RoleName.USER);
        Role adminRole = new Role(null, Role.RoleName.ADMIN);

        entityManager.persist(userRole);
        entityManager.persist(adminRole);

        User user = User.builder().username("admin").email("admin@admin.com")
                .password(passwordEncoder.encode("admin.123")).build();
        user.addRole(adminRole);
        entityManager.persist(user);
    }
}
