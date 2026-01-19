package com.xyz.vnsiva.task.todo.mapper;

import com.xyz.vnsiva.task.todo.Todo;
import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoMapper {
    public TodoResponse toResponse(Todo todo) {
        return new TodoResponse(
                todo.getTitle(),
                Optional.ofNullable(todo.getBody()),
                todo.getDueDate(),
                todo.getCompleted()
        );
    }
}
