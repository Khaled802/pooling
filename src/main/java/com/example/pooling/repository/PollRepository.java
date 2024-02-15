package com.example.pooling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pooling.entity.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {
    @Query("""
            SELECT p FROM Poll p
            JOIN FETCH p.choices
            """)
    List<Poll> findAllWithJoin();

    
    @Query("""
            SELECT p FROM Poll p
            JOIN FETCH p.choices
            WHERE p.id IN :ids
            """)
    List<Poll> findByIdIn(@Param("ids") List<Long> ids);
}
