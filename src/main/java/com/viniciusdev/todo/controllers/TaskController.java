package com.viniciusdev.todo.controllers;

import com.viniciusdev.todo.dtos.requests.TaskRequest;
import com.viniciusdev.todo.dtos.responses.TaskResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.viniciusdev.todo.services.TaskService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> listAll() {
        return ResponseEntity.ok().body(
                taskService.listAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(
                taskService.findById(id)
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request, UriComponentsBuilder uriBuilder) {

        TaskResponse savedTask = taskService.create(request, uriBuilder);

        URI location = uriBuilder
                .path("/task/{id}")
                .buildAndExpand(savedTask.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(savedTask);

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable UUID id, @RequestBody TaskRequest request) {
        return ResponseEntity.ok().body(taskService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBtId(@PathVariable UUID id) {

        taskService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
