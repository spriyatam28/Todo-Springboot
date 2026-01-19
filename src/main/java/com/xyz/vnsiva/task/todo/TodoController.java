package com.xyz.vnsiva.task.todo;

import com.xyz.vnsiva.task.todo.dto.TodoRequest;
import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TodoResponse>> allTodo(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.allTodo(userId));
    }

    @GetMapping("/{userId}/{todoId}")
    public ResponseEntity<TodoResponse> todoById(@PathVariable Long userId, @PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.todoById(userId, todoId));
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.create(todo));
    }

    @PutMapping("/{userId}/{todoId}")
    public ResponseEntity<TodoResponse> updateTodo(@PathVariable Long userId, @PathVariable Long todoId, @Valid @RequestBody TodoRequest todoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.update(userId, todoId, todoRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Integer> deleteAllTodos(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.deleteAll(userId));
    }
}
