package com.sparkagency.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                "RUNTIME_ERROR",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        ErrorResponse error = new ErrorResponse(
                "IO_ERROR",
                "File processing error: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        ErrorResponse error = new ErrorResponse(
                "FILE_TOO_LARGE",
                "File size exceeds maximum allowed limit",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(error);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
                "INVALID_ARGUMENT",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "INTERNAL_ERROR",
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String code;
        private String message;
        private LocalDateTime timestamp;
    }
}