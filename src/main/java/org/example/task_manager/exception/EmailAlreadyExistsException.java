package org.example.task_manager.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super("User with email " + message + " already exists");
    }
}
