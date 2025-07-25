package org.api.stockmarket.indexfund.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IndexFundExceptionHandler {

    @ExceptionHandler(IndexFundException.class)
    public ResponseEntity<String> indexFundException(IndexFundException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
