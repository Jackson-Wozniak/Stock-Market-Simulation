package org.api.stockmarket.stocks.stock.defaults;

import org.api.stockmarket.stocks.stock.enums.MarketCap;

public class DefaultStockPrices {

    public static final double DEFAULT_LARGE_CAP_PRICE = 100.0;
    public static final double DEFAULT_MID_CAP_PRICE = 20.0;
    public static final double DEFAULT_SMALL_CAP_PRICE = 5.0;

    public static double getDefaultPriceWithCap(MarketCap marketCap){
        return switch (marketCap) {
            case Large -> DEFAULT_LARGE_CAP_PRICE;
            case Mid -> DEFAULT_MID_CAP_PRICE;
            case Small -> DEFAULT_SMALL_CAP_PRICE;
        };
    }
}
