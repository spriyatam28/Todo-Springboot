package com.xyz.vnsiva.task.todo;

import com.xyz.vnsiva.task.common.constants.ExceptionConstants;
import com.xyz.vnsiva.task.todo.dto.TodoRequest;
import com.xyz.vnsiva.task.todo.dto.TodoResponse;
import com.xyz.vnsiva.task.todo.exception.TodoNotFoundException;
import com.xyz.vnsiva.task.todo.mapper.TodoMapper;
import com.xyz.vnsiva.task.user.UserService;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import com.xyz.vnsiva.task.user.exception.UserNotFoundException;
import com.xyz.vnsiva.task.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public TodoService(
            TodoRepository todoRepository,
            TodoMapper todoMapper,
            UserService userService,
            UserMapper userMapper
    ) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<TodoResponse> allTodo(Long userId) {
        UserResponse user = userService.getUserByID(userId);

        if (user == null) {
            throw new UserNotFoundException(userId);
        }

        return todoRepository.findTodosByUserId(userId)
                .stream()
                .map(todoMapper::toResponse)
                .toList();
    }

    public TodoResponse todoById(Long userId, Long todoId) {
        Todo todo = todoRepository.findByUserIdAndTodoId(userId, todoId)
                .orElseThrow(() -> new TodoNotFoundException(ExceptionConstants.todoNotFoundException));

        return todoMapper.toResponse(todo);
    }

    public List<TodoResponse> todoByCompletionStatus(Long userId, Boolean completed) {
        return todoRepository.todoByCompletionStatus(userId, completed)
                .stream()
                .map(todoMapper::toResponse)
                .toList();
    }

    @Transactional
    public TodoResponse create(TodoRequest todoDto) {
        UserResponse savedUser = userService.getUserByID(todoDto.userId());
        Todo todo = new Todo();

        todo.setTitle(todoDto.title());
        todo.setBody(todoDto.body().orElse(null));
        todo.setDueDate(todoDto.dueDate());
        todo.setCompleted(todoDto.completed());
        todo.setUser(userMapper.responseToEntity(savedUser));

        Todo savedTodo = todoRepository.save(todo);

        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public TodoResponse update(Long userId, Long todoId, TodoRequest todoDto) {
        Todo savedTodo = todoRepository.findByUserIdAndTodoId(userId, todoId)
                .orElseThrow(() -> new TodoNotFoundException(ExceptionConstants.todoNotFoundException));

        if (!savedTodo.getTitle().equals(todoDto.title())) {
            savedTodo.setTitle(todoDto.title());
        }

        savedTodo.setBody(todoDto.body().orElse(null));
        savedTodo.setCompleted(todoDto.completed());
        savedTodo.setDueDate(todoDto.dueDate());
        savedTodo.setEditedAt(LocalDateTime.now());

        todoRepository.save(savedTodo);

        return todoMapper.toResponse(savedTodo);
    }

    @Transactional
    public Integer deleteAll(Long userId) {
        return todoRepository.deleteAllByUserId(userId);
    }

    @Transactional
    public Integer deleteById(Long userId, Long todoId) {
        int deletedTodoCount = todoRepository.deleteTodoById(userId, todoId);

        if (deletedTodoCount == 0) {
            throw new TodoNotFoundException(ExceptionConstants.todoNotFoundException);
        }

        if (deletedTodoCount > 1) {
            throw new DataIntegrityViolationException(ExceptionConstants.dataIntegrityException);
        }

        return deletedTodoCount;
    }
}
