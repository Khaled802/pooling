package com.example.pooling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteCreationDto {
    private Long id;
    private Long choiceId;
    private Short choiceOrder;
    private Long pollId;
    private Long voterId;
}
