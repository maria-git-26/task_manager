package org.example.task_manager.dto;

public record TaskRequest(
        String title,
        String description
) {}
