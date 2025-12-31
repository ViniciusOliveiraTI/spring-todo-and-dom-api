package com.viniciusdev.todo.services;

import com.viniciusdev.todo.dtos.requests.TaskRequest;
import com.viniciusdev.todo.dtos.responses.TaskResponse;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponse> listAll();
    TaskResponse findById(UUID id);
    TaskResponse create(TaskRequest request, UriComponentsBuilder uriBuilder);
    TaskResponse update(UUID id, TaskRequest request);
    void deleteById(UUID id);
}
