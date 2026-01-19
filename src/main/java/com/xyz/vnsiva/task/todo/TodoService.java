package com.xyz.vnsiva.task.todo;

import com.xyz.vnsiva.task.todo.dto.TodoRequest;
import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import com.xyz.vnsiva.task.todo.mapper.TodoMapper;
import com.xyz.vnsiva.task.user.User;
import com.xyz.vnsiva.task.user.UserService;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import com.xyz.vnsiva.task.user.exception.UserNotFoundException;
import com.xyz.vnsiva.task.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper, UserService userService, UserMapper userMapper) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public TodoResponse todoById(Long userId, Long todoId) {
        Todo todo = todoRepository.findByUserIdAndTodoId(userId, todoId)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found or does not belong to user"));

        return todoMapper.toResponse(todo);
    }

    public TodoResponse create(TodoRequest todoDto) {
        UserResponse savedUser = userService.getUserByID(todoDto.userId());
        Todo todo = new Todo();

        todo.setTitle(todoDto.title());
        todo.setBody(String.valueOf(todoDto.body()));
        todo.setDueDate(todoDto.dueDate());
        todo.setCompleted(todoDto.completed());
        todo.setCreatedAt(todoDto.createdAt());
        todo.setEditedAt(todoDto.editedAt());
        todo.setUser(userMapper.responseToEntity(savedUser));

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toResponse(savedTodo);
    }
}
