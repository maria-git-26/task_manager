package org.example.task_manager.controller;

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


    @GetMapping("/tasks")
    public List<Task> getTasks() {

        return service.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(
            @PathVariable Long id) {

        return service.getTaskById(id);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id) {

        service.deleteTask(id);
    }
}
