package org.example.task_manager.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String passw;
}