package com.xyz.vnsiva.task.todo.helpers;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DueDateValidator.class)
public @interface ValidDueDate {
    String message() default "Due date must after creation date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
