package com.xyz.vnsiva.task.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("""
            SELECT t
            FROM Todo t
            JOIN t.user u
            WHERE u.id = :userId
            AND t.id = :todoId
            """)
    Optional<Todo> findByUserIdAndTodoId(Long userId, Long todoId);

    @Query("SELECT u.todos FROM User u WHERE u.id = :userId")
    List<Todo> findTodosByUserId(Long userId);

    @Modifying
    int deleteAllByUserId(Long userId);
}
