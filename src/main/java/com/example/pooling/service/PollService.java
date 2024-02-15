package com.example.pooling.service;

import java.util.List;

import com.example.pooling.entity.Poll;

public interface PollService {
    Poll create(Poll poll);
    List<Poll> getAll();
}
