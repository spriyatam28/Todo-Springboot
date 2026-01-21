# Todo App built using Springboot

## Tech Stack Overview

| Component  | Technology                  |
|------------|-----------------------------|
| Framework  | Spring Boot 4.0.1           |
| Java       | Java 25                     |
| Database   | PostgreSQL                  |
| ORM        | Spring Data JPA / Hibernate |
| Security   | Spring Security             |
| Build Tool | Maven                       |


## Project Structure:

```
com.xyz.vnsiva.task/
├── TaskApplication.java          # Entry point
├── common/
│   ├── constants/                # Exception message constants
│   └── enums/                    # Role enum
├── config/
│   └── SecurityConfig.java       # Security configuration
├── exceptions/                   # Global exception handling
├── health/                       # Health check endpoint  
├── todo/                         # Todo domain module
│   ├── Todo.java                 # Entity
│   ├── TodoController.java       # REST Controller
│   ├── TodoService.java          # Business logic
│   ├── TodoRepository.java       # Data access
│   ├── dto/                      # Request/Response DTOs
│   ├── helpers/                  # Custom validation
│   └── mapper/                   # Entity DTO mapping
├── user/                         # User domain module
│   ├── User.java                 # Entity
│   ├── UserController.java       # REST Controller
│   ├── UserService.java          # Business logic
│   ├── UserRepository.java       # Data access
│   ├── dto/                      # Request/Response DTOs
│   ├── exception/                # User-specific exceptions
│   └── mapper/                   # Entity DTO mapping
├── room/                         # Room domain module
```

### To run development server:

- If `maven` is installed
```
mvn spring-boot:run
```