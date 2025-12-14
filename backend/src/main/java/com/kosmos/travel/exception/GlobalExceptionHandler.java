package com.kosmos.travel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle Resource Not Found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = ex.getMessage();
        if (message == null || message.trim().isEmpty()) {
            message = "Resource not found.";
        } else if (!message.trim().endsWith(".")) {
            message = message.trim() + ".";
        }
        errorResponse.put("error", message);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            if (message == null) message = "Invalid value.";
            if (!message.trim().endsWith(".")) message = message.trim() + ".";
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle Other Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String message = ex.getMessage();
        if (message == null || message.trim().isEmpty()) {
            message = "Internal server error.";
        } else if (!message.trim().endsWith(".")) {
            message = message.trim() + ".";
        }
        errorResponse.put("error", "Internal Server Error: " + message);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
