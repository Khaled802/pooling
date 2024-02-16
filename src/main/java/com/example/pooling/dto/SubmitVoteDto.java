package com.example.pooling.dto;

import jakarta.validation.constraints.NotNull;

public record SubmitVoteDto(@NotNull Long pollId, @NotNull Short voteOrder) {
    
}
