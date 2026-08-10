package org.example.task_manager.controller;

import jakarta.validation.Valid;
import org.example.task_manager.dto.UserRequest;
import org.example.task_manager.dto.UserResponse;
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
    public List<UserResponse> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable Long id) {

        return service.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable Long id) {

        service.deleteUser(id);
    }


}
