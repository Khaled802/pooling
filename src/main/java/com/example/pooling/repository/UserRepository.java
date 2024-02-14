package com.example.pooling.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pooling.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email, String name);
    List<User> findByIdIn(List<Long> ids);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
