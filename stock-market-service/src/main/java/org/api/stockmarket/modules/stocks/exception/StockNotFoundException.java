package org.api.stockmarket.modules.stocks.exception;

import org.api.stockmarket.core.exception.base.NotFoundException;

public class StockNotFoundException extends NotFoundException {

    public StockNotFoundException(String message) {
        super(message, "StockController");
    }
}
