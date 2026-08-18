package org.example.task_manager.service;

import jakarta.transaction.Transactional;
import org.example.task_manager.dto.TaskRequest;
import org.example.task_manager.dto.TaskResponse;
import org.example.task_manager.dto.UserResponse;
import org.example.task_manager.entity.Task;
import org.example.task_manager.entity.User;
import org.example.task_manager.exception.ResourceNotFoundException;
import org.example.task_manager.repository.TaskRepository;
import org.example.task_manager.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository repository, UserRepository userRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> getAllTasks() {

        User currentUser =
                getCurrentUser();
        return repository.findByUser(currentUser)

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
        User currentUser =
                getCurrentUser();

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setStatus(request.status());
        task.setUser(currentUser);

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

        User currentUser =
                getCurrentUser();

        Task task =
                repository
                        .findByIdAndUser(
                                id,
                                currentUser
                        )
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                        "Task not found"
                                )
                        );

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority()
        );
    }

    public List<TaskResponse> getTasksByUserId(Long userId) {

        User currentUser =
                getCurrentUser();
        return repository.findByIdAndUser(userId,currentUser)
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

    @Transactional
    public void deleteTask(Long id) {
        User currentUser =
                getCurrentUser();

        repository.deleteByIdAndUser(id, currentUser);
        //repository.deleteById(id);
    }

    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        System.out.println(
                "AUTHENTICATION = "
                        + authentication
        );

        System.out.println(
                "USERNAME = "
                        + authentication.getName()
        );

        String username =
                authentication.getName();

        User user =
                userRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        System.out.println(
                "CURRENT USER ID = "
                        + user.getId()
        );

        System.out.println(
                "CURRENT USERNAME = "
                        + user.getUsername()
        );

        return user;
    }
}
