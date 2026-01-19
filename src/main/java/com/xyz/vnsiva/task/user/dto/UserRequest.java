package com.xyz.vnsiva.task.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Name should be not empty")
        @Size(min = 3, max = 200)
        String name,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format")
        @Size(min = 6, max = 320, message = "Email should be between 6 and 320 characters")
        String email
) {}
