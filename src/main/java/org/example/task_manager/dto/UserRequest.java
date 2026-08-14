package org.example.task_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Username cannot be empty")
        @Size(min = 3, max = 30,
                message = "Username must be 3-30 characters")
        String username,
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 5, max = 100,
                message = "Password must be at least 5 characters")
        String password,
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        String email
) {}
