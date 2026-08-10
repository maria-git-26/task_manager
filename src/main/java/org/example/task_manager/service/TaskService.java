package org.example.task_manager.service;

import org.example.task_manager.dto.TaskRequest;
import org.example.task_manager.dto.TaskResponse;
import org.example.task_manager.dto.UserResponse;
import org.example.task_manager.entity.Task;
import org.example.task_manager.exception.ResourceNotFoundException;
import org.example.task_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(
            TaskRepository repository
    ) {
        this.repository = repository;
    }

    public List<TaskResponse> getAllTasks() {

        return repository.findAll()
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getPriority()
                ))
                .toList();
    }

    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setStatus(request.status());

        Task savedTask = repository.save(task);

        return new TaskResponse(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getPriority()
        );
    }

    public TaskResponse getTaskById(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Task with id "
                                        + id +
                                        " not found"));

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority()
        );
    }

    public List<TaskResponse> getTasksByUserId(Long userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(task -> new TaskResponse(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getPriority()
                ))
                .toList();
    }

    public void deleteTask(Long id) {

        repository.deleteById(id);
    }
}
