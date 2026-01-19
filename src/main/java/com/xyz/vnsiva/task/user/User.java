package com.xyz.vnsiva.task.user;
import com.xyz.vnsiva.task.todo.Todo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", indexes = {@Index(name = "idx_users_email", columnList = "email", unique = true)})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    // TODO: Add role and user status later
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @Enumerated(EnumType.STRING)
//    private UserStatus userStatus;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Todo> getTodos() { return todos; }
    public void setTodos(List<Todo> todos) { this.todos = todos; }

    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setUser(this);
    }
}
