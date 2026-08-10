package org.example.task_manager.service;

import lombok.RequiredArgsConstructor;
import org.example.task_manager.dto.TaskResponse;
import org.example.task_manager.dto.UserRequest;
import org.example.task_manager.dto.UserResponse;
import org.example.task_manager.entity.Task;
import org.example.task_manager.entity.User;
import org.example.task_manager.exception.EmailAlreadyExistsException;
import org.example.task_manager.exception.ResourceNotFoundException;
import org.example.task_manager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                ))
                .toList();
    }

    public UserResponse createUser(UserRequest request) {

        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(
                    request.email()
            );
        }

        User user = new User();
        String encodedPassword =
                passwordEncoder.encode(request.passw());

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassw(encodedPassword);

        repository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());

    }

    public UserResponse getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User with id "
                                        + id +
                                        " not found"));

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }

    //saving to github
}
