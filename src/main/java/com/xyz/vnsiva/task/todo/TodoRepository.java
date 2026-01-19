package com.xyz.vnsiva.task.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Long, Todo> {
    @Query("SELECT title, body,dueDate, completed FROM Todo WHERE id = :id")
    Todo findById(Long id);
}
