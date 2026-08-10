package org.example.task_manager.dto;

public record LoginRequest(
        String email,
        String password
) {}
