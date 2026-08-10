package org.example.task_manager.controller;

import jakarta.validation.Valid;
import org.example.task_manager.dto.LoginRequest;
import org.example.task_manager.dto.LoginResponse;
import org.example.task_manager.dto.UserRequest;
import org.example.task_manager.dto.UserResponse;
import org.example.task_manager.service.AuthService;
import org.example.task_manager.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }



    @PostMapping("/register")
    public UserResponse register(
            @Valid @RequestBody UserRequest request) {

        return userService.createUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}
