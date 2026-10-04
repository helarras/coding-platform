package com.helarras.codingplatform.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        System.err.println("CRITICAL UNHANDLED ERROR:");
        e.printStackTrace();

        var response = ErrorResponse.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected internal server error occurred"
        );

        return ResponseEntity
                .status(response.status())
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException e) {
        var response = ErrorResponse.of(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        return ResponseEntity
                .status(response.status())
                .body(response);
    }

    @ExceptionHandler({
            IllegalSubmissionStateException.class,
            ProblemPublishingException.class
    })
    public ResponseEntity<ErrorResponse> handleDomainStateExceptions(RuntimeException e) {
        var response = ErrorResponse.of(
                HttpStatus.CONFLICT,
                e.getMessage()
        );
        return ResponseEntity
                .status(response.status())
                .body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
        var response = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getMessage()
        );
        return ResponseEntity
                .status(response.status())
                .body(response);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidToken(InvalidTokenException e) {
        var response = ErrorResponse.of(
                HttpStatus.UNAUTHORIZED,
                e.getMessage()
        );
        return ResponseEntity
                .status(response.status())
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Invalid input provided");
        var response = ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                errorMessage
        );
        return ResponseEntity
                .status(response.status())
                .body(response);
    }
}
