package org.example.task_manager.dto;

public record TaskResponse(
        Long id,
        String title,
        String description
) {}
