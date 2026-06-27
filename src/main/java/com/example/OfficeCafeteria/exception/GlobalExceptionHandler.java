package com.example.OfficeCafeteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateResourceException.class})
    public ResponseEntity<GlobalExceptionWrapper> handleDuplicateResourceException(DuplicateResourceException ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<GlobalExceptionWrapper> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidRequestException.class})
    public ResponseEntity<GlobalExceptionWrapper> handleInvalidRequestException(InvalidRequestException ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<GlobalExceptionWrapper> handleException(Exception ex) {
        return new ResponseEntity<>(new GlobalExceptionWrapper(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
