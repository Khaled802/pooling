package com.example.pooling.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.pooling.entity.Choice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CreatingPollDto {
    @NotBlank
    @Size(max = 150)
    private String question;

    @Size(min = 2, max = 6)
    private List<CreatingChoiceDto> choices = new ArrayList<>();
}
