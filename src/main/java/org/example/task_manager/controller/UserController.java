package org.example.task_manager.controller;

import org.example.task_manager.entity.Task;
import org.example.task_manager.entity.User;
import org.example.task_manager.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(
            UserService service
    ) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(
            @PathVariable Long id) {

        return service.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteTask(
            @PathVariable Long id) {

        service.deleteUser(id);
    }
}
