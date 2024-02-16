package com.example.pooling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.pooling.model.Vote;

import jakarta.transaction.Transactional;

import com.example.pooling.model.ChoiceResult;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("""
            SELECT new com.example.pooling.model.ChoiceResult(v.choice.id, v.choice.order, COUNT(v.id))
            FROM Vote v
            WHERE v.poll.id = :pollId
            GROUP BY v.choice.id
            """)
    List<ChoiceResult> getPollChoicesResult(@Param("pollId") Long pollId);

    // @Command("""
    //         DELETE FROM Vote v WHERE v.poll.id = :pollId AND v.voter.id = :userId
    //         """)
    @Transactional
    void deleteByPollIdAndVoterId(Long pollId, Long userId);
}