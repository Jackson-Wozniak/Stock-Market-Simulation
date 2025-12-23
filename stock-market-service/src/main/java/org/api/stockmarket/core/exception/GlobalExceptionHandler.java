package org.api.stockmarket.core.exception;

import org.api.stockmarket.core.entity.BaseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseEntity.NotFoundException.class)
    public ResponseEntity<String> notFoundException(BaseEntity.NotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestException(BadRequestException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
