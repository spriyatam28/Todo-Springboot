package com.xyz.vnsiva.task.todo.helpers;

import com.xyz.vnsiva.task.todo.Todo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DueDateValidator implements ConstraintValidator<ValidDueDate, Todo> {
    @Override
    public boolean isValid(Todo todo, ConstraintValidatorContext context) {
        if (todo.getDueDate() == null || todo.getEditedAt() == null) {
            return true;
        }

        return todo.getDueDate().isAfter(todo.getEditedAt());
    }
}
