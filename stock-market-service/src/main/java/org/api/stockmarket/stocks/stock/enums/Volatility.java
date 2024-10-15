package org.api.stockmarket.stocks.stock.enums;

public enum Volatility {
    STABLE,
    NORMAL,
    VOLATILE,
    EXTRA_VOLATILE;

    public int multiplier(){
        return switch(this){
            case STABLE -> 0;
            case NORMAL -> 1;
            case VOLATILE -> 2;
            case EXTRA_VOLATILE -> 3;
        };
    }
}
