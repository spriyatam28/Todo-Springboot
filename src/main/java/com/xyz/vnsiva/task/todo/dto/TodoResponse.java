package com.xyz.vnsiva.task.todo.dto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public record TodoResponse(
        String title,
        Optional<String> body,
        LocalDateTime dueDate,
        Boolean completed
) {}
