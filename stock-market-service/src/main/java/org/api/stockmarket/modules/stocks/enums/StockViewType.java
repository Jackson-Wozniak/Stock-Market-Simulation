package org.api.stockmarket.modules.stocks.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.api.stockmarket.core.exception.BadRequestException;

public enum StockViewType {
    BASIC,
    DETAILED,
    FULL;

    @JsonCreator
    public static StockViewType fromString(String value) {
        return switch (value.toLowerCase()){
            case "basic" -> BASIC;
            case "detailed" -> DETAILED;
            case "full" -> FULL;
            default -> throw new BadRequestException("Stock view type is invalid");
        };
    }
}
