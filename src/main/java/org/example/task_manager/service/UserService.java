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
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;


    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public UserResponse createUser(UserRequest request) {
//        User user = User.builder()
//                .username(request.username())
//                .email(request.email())
//                .passw(request.passw())
//                .build();
//        User savedUser = repository.save(user);



        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(
                    request.email()
            );
        }

        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassw(request.passw());

        repository.save(user);

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());

    }

    public User getUserById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User with id "
                                        + id +
                                        " not found"));
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }

    //saving to github
}
