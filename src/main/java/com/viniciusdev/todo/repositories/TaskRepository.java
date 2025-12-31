package com.viniciusdev.todo.repositories;

import com.viniciusdev.todo.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    boolean existsById(UUID id);
}
