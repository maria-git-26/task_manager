package org.example.task_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.LoginRequest;
import org.example.task_manager.dto.LoginResponse;
import org.example.task_manager.dto.RegisterRequest;
import org.example.task_manager.entity.User;
import org.example.task_manager.repository.UserRepository;
import org.example.task_manager.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User with email "
                                        + request.email()
                                        + " not found"
                        )
                );

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new BadCredentialsException(
                    "Invalid email or password"
            );
        }

        String token =
                jwtService.generateToken(user.getUsername());

        return new LoginResponse(token);
    }


}
