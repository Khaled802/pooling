package com.example.pooling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.pooling.entity.Choice;
import com.example.pooling.entity.Poll;
import com.example.pooling.repository.PollRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PollServiceImp implements PollService {
    private final PollRepository pollRepository;

    @Override
    public Poll create(Poll poll) {
        short order = 1;
        for (Choice choice : poll.getChoices()) {
            choice.setOrder(order++);
            choice.setPoll(poll);
        }
        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> getAll() {
        return pollRepository.findAllWithJoin();
    }
}
