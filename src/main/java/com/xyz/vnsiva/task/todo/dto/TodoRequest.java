package com.xyz.vnsiva.task.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

public record TodoRequest(
        String title,
        Optional<String> body,
        @JsonProperty("user_id")
        Long userId,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("edited_at")
        LocalDateTime editedAt,
        @JsonProperty("due_date")
        @NotNull
        @Future
        LocalDateTime dueDate,
        Boolean completed
) {}
