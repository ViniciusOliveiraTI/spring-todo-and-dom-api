package com.viniciusdev.todo.dtos.requests;

import java.time.LocalDate;

public record TaskRequest(String title, String description, LocalDate startDate, LocalDate endDate, String status) {}
