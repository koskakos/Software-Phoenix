package com.software.phoenix.controller;

import com.software.phoenix.model.response.ApiError;
import org.hibernate.NonUniqueObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
        ApiError apiError = new ApiError("Entity not found", ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NonUniqueObjectException.class)
    protected ResponseEntity<?> handleNonUniqueObjectException(NonUniqueObjectException ex, WebRequest webRequest) {
        ApiError apiError = new ApiError("Login already exists",
                String.format("User with login '%s' already exists", ex.getEntityName()));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
