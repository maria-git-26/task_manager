package org.example.task_manager.dto;

public record UserResponse(
        Long id,
        String username,
        String email
) {}
