package org.api.stockmarket.modules.stocks.exception;

public class StockNotFoundException extends Exception {

    public StockNotFoundException(String message) {
        super(message);
    }
}
