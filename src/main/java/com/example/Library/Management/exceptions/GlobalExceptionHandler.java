package com.example.Library.Management.exceptions;

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
    @ExceptionHandler ResponseEntity<ExceptionResponse> handleException(ResourceNotFound exc) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
            exc.getMessage(),404,System.currentTimeMillis(),exc.getLocalizedMessage());
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler ResponseEntity<ExceptionResponse>  handleException(MethodArgumentNotValidException exc) {
        Map<String,String> errorsMap = new HashMap<>();
        for(FieldError error: exc.getFieldErrors()){
            errorsMap.put(error.getField(),error.getDefaultMessage());
        }
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "Invalid request content",
                400,System.currentTimeMillis(),errorsMap.toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler ResponseEntity<ExceptionResponse> handleException(ResourceAlreadyExists exc) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                exc.getMessage(),400,System.currentTimeMillis(), exc.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
