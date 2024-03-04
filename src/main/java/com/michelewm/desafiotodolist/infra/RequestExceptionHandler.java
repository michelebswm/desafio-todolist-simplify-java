package com.michelewm.desafiotodolist.infra;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.michelewm.desafiotodolist.services.exceptions.ResourceNotFoundException;
import com.michelewm.desafiotodolist.services.exceptions.ValidationTaskException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RequestExceptionHandler {

     @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ValidationTaskException.class)
    public ResponseEntity<StandardError> validationTask(ValidationTaskException e, HttpServletRequest request){
        String error = "Error Validation";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
