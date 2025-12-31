package com.viniciusdev.todo.infra;

import com.viniciusdev.todo.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getMessage());
        pd.setTitle("Resource not found");
        pd.setType(URI.create("http://localhost:8080/problems/not-found"));
        pd.setInstance(URI.create(request.getRequestURI()));
        pd.setProperties(Map.of("error", e.getCode(), "timestamp", Instant.now()));
        return pd;
    }

}
