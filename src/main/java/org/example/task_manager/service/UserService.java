package org.example.task_manager.service;

import org.example.task_manager.entity.User;
import org.example.task_manager.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(
            UserRepository repository
    ) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void createUser(User user) {
        repository.save(user);
    }

    public User getUserById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }

    public void deleteUser(Long id) {

        repository.deleteById(id);
    }
}
