package org.example.task_manager.controller;

import jakarta.validation.Valid;
import org.example.task_manager.dto.TaskRequest;
import org.example.task_manager.dto.TaskResponse;
import org.example.task_manager.entity.Task;
import org.example.task_manager.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(
            TaskService service
    ) {
        this.service = service;
    }


    @GetMapping("/getTasks")
    public List<TaskResponse> getTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponse getTask(
            @PathVariable Long id) {

        return service.getTaskById(id);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getTasksByUser(
            @PathVariable Long userId) {

        return service.getTasksByUserId(userId);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id) {

        service.deleteTask(id);
    }

    @PostMapping
    public TaskResponse createTask(@Valid @RequestBody TaskRequest request) {
        System.out.println(">>> CREATE TASK CONTROLLER");
        return service.createTask(request);
    }
}
