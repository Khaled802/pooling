package com.example.pooling.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PollRetraiveDto {
    private Long id;

    private String question;

    private List<ChoiceRetraiveDto> choices;
}
