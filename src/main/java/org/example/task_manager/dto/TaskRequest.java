package org.example.task_manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String title,
        @Size(max = 500)
        String description,
        String priority,
        String status
) {}
