package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NotMatchException.class)
    public ResponseEntity<ErrorResult> handle(NotMatchException ex) {
        ErrorResult errorResult = new ErrorResult(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
        String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ErrorResult errorResult = new ErrorResult(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
//        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
//
//        String message = "";
//        for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
//            message = constraint.getMessage();
//            break;
//        }
//        ErrorResult errorResult = new ErrorResult(message);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
//    }
}