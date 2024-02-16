package com.example.pooling.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pooling.dto.SubmitVoteDto;
import com.example.pooling.dto.VoteCreationDto;
import com.example.pooling.model.ChoiceResult;
import com.example.pooling.service.VoteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/votes")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;
    private final ModelMapper modelMapper;

    @PostMapping
    public VoteCreationDto addVote(@Valid @RequestBody SubmitVoteDto submitVoteDto, Authentication authentication) {
        return modelMapper.map(voteService.addVote(authentication.getName(), submitVoteDto.pollId(), submitVoteDto.voteOrder()), VoteCreationDto.class);
    }
    

    @GetMapping
    public List<ChoiceResult> getResults(@RequestParam("pollId") Long pollId) {
        return voteService.getPollResults(pollId);
    }

    @DeleteMapping("/on/{pollId}")
    public void removeVote(@PathVariable("pollId") Long pollId, Authentication authentication) {
        voteService.removeVote(authentication.getName(), pollId);
    }
}
