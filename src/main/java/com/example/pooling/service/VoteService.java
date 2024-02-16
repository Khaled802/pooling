package com.example.pooling.service;

import java.util.List;

import com.example.pooling.model.ChoiceResult;
import com.example.pooling.model.Vote;

public interface VoteService {
    Vote addVote(String username, Long pollId, Short choiceOrder);
    List<ChoiceResult> getPollResults(Long pollId);
    void removeVote(String username, Long pollId);
}
