package com.xyz.vnsiva.task.todo;

import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<Todo>> allTodo() {
        List<Todo> todos = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> todoById(@PathVariable Long userId, @PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.todoById(userId, todoId));
    }
}
