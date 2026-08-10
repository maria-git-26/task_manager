package org.example.task_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.LoginRequest;
import org.example.task_manager.dto.LoginResponse;
import org.example.task_manager.entity.User;
import org.example.task_manager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        User user =
                userRepository.findByEmail(
                                request.email())
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"));

        boolean matches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPassword());

        if (!matches) {
            throw new RuntimeException(
                    "Wrong password");
        }
        return new LoginResponse(
                "JWT_TOKEN");
    }
}
