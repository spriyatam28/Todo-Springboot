package com.xyz.vnsiva.task.todo.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record TodoResponse(
        Long id,
        String title,
        Optional<String> body,
        LocalDateTime dueDate,
        Boolean completed
) {}
