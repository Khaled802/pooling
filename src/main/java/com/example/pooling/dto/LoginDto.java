package com.example.pooling.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDto(
    @NotNull
    String username,
    
    @NotNull
    String password
) {}
