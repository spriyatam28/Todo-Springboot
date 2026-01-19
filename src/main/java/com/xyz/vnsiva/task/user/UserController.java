package com.xyz.vnsiva.task.user;

import com.xyz.vnsiva.task.user.dto.UserRequest;
import com.xyz.vnsiva.task.user.dto.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    // `@Autowired` is not needed when there is only one constructor. Springboot automatically adds it

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> users() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserByID(@PathVariable Long id) {
        return userService.getUserByID(id);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(user));
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest updatedUser) {
        return userService.update(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserByID(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.ok().build();
    }
}
