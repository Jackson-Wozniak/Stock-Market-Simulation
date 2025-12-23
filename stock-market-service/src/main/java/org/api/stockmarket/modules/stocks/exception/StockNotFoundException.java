package org.api.stockmarket.modules.stocks.exception;

import org.api.stockmarket.core.entity.BaseEntity;

public class StockNotFoundException extends BaseEntity.NotFoundException {

    public StockNotFoundException(String message) {
        super(message, "StockController");
    }
}
