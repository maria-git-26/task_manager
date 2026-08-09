package org.example.task_manager.dto;

public record UserRequest(
        String username,
        String passw,
        String email
) {}
