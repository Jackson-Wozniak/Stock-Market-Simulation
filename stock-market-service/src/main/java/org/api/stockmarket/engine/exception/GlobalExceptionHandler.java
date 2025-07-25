package org.api.stockmarket.market.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MarketExceptionHandler {

    @ExceptionHandler(DateFormatException.class)
    public ResponseEntity<String> dateFormatException(DateFormatException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
