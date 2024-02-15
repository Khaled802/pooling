package com.example.pooling.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pooling.dto.CreatingPollDto;
import com.example.pooling.dto.PollRetraiveDto;
import com.example.pooling.entity.Poll;
import com.example.pooling.service.PollService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/polls")
@Slf4j
@RequiredArgsConstructor
public class PollController {
    private final PollService pollService;
    private final ModelMapper modelMapper;

    @PostMapping
    @Secured("ADMIN")
    public PollRetraiveDto createPoll(@Valid @RequestBody CreatingPollDto pollDto) {
        Poll pollResult = pollService.create(modelMapper.map(pollDto, Poll.class));
        log.info("poll result: {}", pollResult);
        return modelMapper.map(pollResult, PollRetraiveDto.class);
    }

    @GetMapping
    public List<PollRetraiveDto> getMethodName() {
        return pollService.getAll().stream().map(poll -> modelMapper.map(poll, PollRetraiveDto.class)).toList();
    }
    
}
