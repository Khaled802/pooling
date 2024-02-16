package com.example.pooling.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pooling.model.Choice;
import com.example.pooling.model.Poll;
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

    @Override
    public Poll getById(Long id) {
        return pollRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found poll"));
    }
}
