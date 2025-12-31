package com.viniciusdev.todo.dtos.responses;

import com.viniciusdev.todo.entities.Task;

import java.time.LocalDate;
import java.util.UUID;

public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    public TaskResponse(UUID id, String title, String description, LocalDate startDate, LocalDate endDate, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getDescription(), task.getStartDate(), task.getEndDate(), task.getStatus());
    }
}
