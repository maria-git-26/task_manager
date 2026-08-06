package org.example.task_manager.service;

import org.example.task_manager.entity.Task;
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

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public void createTask(Task task) {
        repository.save(task);
    }

    public Task getTaskById(Long id) {

        return repository.findById(id)
                .orElse(null);
    }

    public void deleteTask(Long id) {

        repository.deleteById(id);
    }
}
