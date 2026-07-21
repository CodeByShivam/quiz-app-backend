package com.quizapp.dto;

public record LoginResponse(
        String token,
        String username,
        String role
) {}