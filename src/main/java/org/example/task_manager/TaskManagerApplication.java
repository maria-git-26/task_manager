package org.example.task_manager;

import org.example.task_manager.entity.Task;
import org.example.task_manager.repository.TaskRepository;
import org.example.task_manager.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(TaskService service) {
//
//        return args -> {
//            service.createTask();
//        };
//    }
}


