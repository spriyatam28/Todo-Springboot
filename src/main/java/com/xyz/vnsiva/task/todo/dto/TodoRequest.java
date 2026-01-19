package com.xyz.vnsiva.task.todo.dto;

import java.time.LocalDateTime;

public record TodoRequest(
        String title,
        String body,
        Integer userId,
        LocalDateTime createdAt,
        LocalDateTime editedAt,
        LocalDateTime dueDate,
        Boolean completed
) {}
