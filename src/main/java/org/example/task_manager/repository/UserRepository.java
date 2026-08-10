package org.example.task_manager.repository;
import java.util.Optional;
import org.example.task_manager.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
//testing commit

