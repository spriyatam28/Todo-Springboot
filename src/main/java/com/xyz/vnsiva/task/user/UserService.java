package com.xyz.vnsiva.task.user;

import com.xyz.vnsiva.task.user.dto.UserRequest;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import com.xyz.vnsiva.task.user.exception.DuplicateEmailException;
import com.xyz.vnsiva.task.user.exception.UserNotFoundException;
import com.xyz.vnsiva.task.user.mapper.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserResponse> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserByID(Long id) {
        User savedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponse(savedUser);
    }

    public UserResponse create(UserRequest user) {
        if (userRepository.findByEmail(user.email())) {
            throw new DuplicateEmailException(user.email());
        }

        User newUser = userMapper.toEntity(user);

        return userMapper.toResponse(userRepository.save(newUser));
    }

    public UserResponse update(Long id, UserRequest updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(updatedUser.name());
        user.setEmail(updatedUser.email());

        return userMapper.toResponse(userRepository.save(user));
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(user);
    }
}
