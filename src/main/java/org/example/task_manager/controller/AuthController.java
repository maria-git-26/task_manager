package org.example.task_manager.controller;

import jakarta.validation.Valid;
import org.example.task_manager.dto.UserRequest;
import org.example.task_manager.dto.UserResponse;
import org.example.task_manager.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(
            @Valid @RequestBody UserRequest request) {

        return userService.createUser(request);
    }
}
