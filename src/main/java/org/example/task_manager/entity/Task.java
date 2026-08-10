package org.example.task_manager.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}


