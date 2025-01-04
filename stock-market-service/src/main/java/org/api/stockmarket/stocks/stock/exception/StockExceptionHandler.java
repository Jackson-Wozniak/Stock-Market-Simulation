package org.api.stockmarket.stocks.stock.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StockExceptionHandler {

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<String> stockNotFound(StockNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
