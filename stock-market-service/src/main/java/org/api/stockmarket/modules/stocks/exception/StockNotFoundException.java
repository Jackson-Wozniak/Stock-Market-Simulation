package org.api.stockmarket.stocks.stock.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StockNotFoundException extends RuntimeException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public StockNotFoundException(String message) {
        super(message);
    }
}
