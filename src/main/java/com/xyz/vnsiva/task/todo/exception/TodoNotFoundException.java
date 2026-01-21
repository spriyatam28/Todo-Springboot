package com.xyz.vnsiva.task.todo.exception;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String message) {
        super(message);
    }
}
