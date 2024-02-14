package com.example.pooling.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.pooling.entity.Role;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role userRole = new Role(null, Role.RoleName.USER);
        Role adminRole = new Role(null, Role.RoleName.ADMIN);

        entityManager.persist(userRole);
        entityManager.persist(adminRole);
    }
}
