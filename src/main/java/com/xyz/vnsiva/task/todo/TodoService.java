package com.xyz.vnsiva.task.todo;

import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import com.xyz.vnsiva.task.todo.mapper.TodoMapper;
import com.xyz.vnsiva.task.user.UserService;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserService userService;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper, UserService userService) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    public TodoResponse todoById(Long userId, Long todoId) {
        UserResponse savedUser = userService.getUserByID(userId);

        return todoMapper.toResponse(todoRepository.findById(todoId));
    }
}
