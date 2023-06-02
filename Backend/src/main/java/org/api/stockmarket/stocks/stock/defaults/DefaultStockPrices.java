package org.api.stockmarket.stocks.stock.defaults;

import org.api.stockmarket.stocks.stock.enums.MarketCap;

import java.util.Random;

public class DefaultStockPrices {

    private static final Random random = new Random();

    private static final double DEFAULT_LARGE_CAP_PRICE = 100.0;
    private static final double DEFAULT_MID_CAP_PRICE = 20.0;
    private static final double DEFAULT_SMALL_CAP_PRICE = 5.0;

    public static double getDefaultPriceWithCap(MarketCap marketCap) {
        return switch (marketCap) {
            case Large -> DEFAULT_LARGE_CAP_PRICE + random.nextInt(-10, 10);
            case Mid -> DEFAULT_MID_CAP_PRICE + random.nextInt(-5, 5);
            case Small -> DEFAULT_SMALL_CAP_PRICE + random.nextInt(-2, 2);
        };
    }
}
