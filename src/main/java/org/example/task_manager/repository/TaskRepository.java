package org.example.task_manager.repository;
import org.example.task_manager.entity.Task;
import org.example.task_manager.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUser(User user);

    Optional<Task> findByIdAndUser(
            Long id,
            User user);

    Optional<Task> deleteByIdAndUser(
            Long id,
            User user);
}
