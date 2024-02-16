package com.example.pooling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pooling.model.Choice;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    Optional<Choice> findByOrderAndPollId(Short order, Long pollId);
}
