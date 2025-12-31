package com.viniciusdev.todo.services.impl;

import com.viniciusdev.todo.dtos.requests.TaskRequest;
import com.viniciusdev.todo.dtos.responses.TaskResponse;
import com.viniciusdev.todo.entities.Task;
import com.viniciusdev.todo.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.viniciusdev.todo.repositories.TaskRepository;
import com.viniciusdev.todo.services.TaskService;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponse> listAll() {
        return taskRepository.findAll()
                .stream().map(TaskResponse::fromEntity)
                .toList();
    }

    @Override
    public TaskResponse findById(UUID id) {
        return TaskResponse.fromEntity(getTaskOrThrow(id));
    }

    @Override
    public TaskResponse create(TaskRequest request, UriComponentsBuilder uriBuilder) {

        Task newTask = new Task();

        setTaskAttrs(newTask, request);

        Task savedTask = taskRepository.save(newTask);

        return TaskResponse.fromEntity(savedTask);

    }

    @Override
    public TaskResponse update(UUID id, TaskRequest request) {

        Task task = getTaskOrThrow(id);

        setTaskAttrs(task, request);

        return TaskResponse.fromEntity(task);

    }

    @Override
    public void deleteById(UUID id) {

        if (!taskRepository.existsById(id)) {
            throw new NotFoundException("Task with id '%s' not found".formatted(id));
        }

        taskRepository.deleteById(id);

    }

    private Task getTaskOrThrow(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id '%s' not found".formatted(id)));
    }

    private void setTaskAttrs(Task task, TaskRequest request) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStartDate(request.startDate());
        task.setEndDate(request.endDate());
        task.setStatus(request.status());
    }

}
