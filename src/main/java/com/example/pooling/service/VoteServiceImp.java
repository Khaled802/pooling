package com.example.pooling.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pooling.model.Choice;
import com.example.pooling.model.ChoiceResult;
import com.example.pooling.model.Poll;
import com.example.pooling.model.User;
import com.example.pooling.model.Vote;
import com.example.pooling.repository.ChoiceRepository;
import com.example.pooling.repository.VoteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoteServiceImp implements VoteService {
    private final VoteRepository voteRepository;
    private final ChoiceRepository choiceRepository;
    private final UserService userService;
    private final PollService pollService;

    @Override
    @Transactional
    public Vote addVote(String username, Long pollId, Short choiceOrder) {
        User user = userService.getUserByUsername(username);
        Poll poll = pollService.getById(pollId);
        Choice choice = choiceRepository.findByOrderAndPollId(choiceOrder, pollId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found choice"));
        Vote vote = Vote.builder().voter(user).poll(poll).choice(choice).build();
        if (vote == null) throw new RuntimeException("problem in creating vote");
        return voteRepository.save(vote);
        
    }

    @Override
    public List<ChoiceResult> getPollResults(Long pollId) {
        return voteRepository.getPollChoicesResult(pollId);
    }

    @Override
    public void removeVote(String username, Long pollId) {
        User user = userService.getUserByUsername(username);
        voteRepository.deleteByPollIdAndVoterId(pollId, user.getId());
    }

}
