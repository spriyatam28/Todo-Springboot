package com.xyz.vnsiva.task.user.mapper;

import com.xyz.vnsiva.task.user.User;
import com.xyz.vnsiva.task.user.dto.UserRequest;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserRequest userRequest) {
        User user = new User();

        user.setName(userRequest.name());
        user.setEmail(userRequest.email());

        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public User responseToEntity(UserResponse userResponse) {
        User user = new User();

        user.setId(userResponse.id());
        user.setName(userResponse.name());
        user.setEmail(userResponse.email());

        return user;
    }
}
