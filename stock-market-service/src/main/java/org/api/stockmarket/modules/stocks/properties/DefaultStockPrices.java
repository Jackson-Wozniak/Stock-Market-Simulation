package org.api.stockmarket.modules.stocks.properties;

import org.api.stockmarket.modules.stocks.enums.MarketCap;

import java.util.Random;

public class DefaultStockPrices {

    private static final Random random = new Random();

    public static final double DEFAULT_LARGE_CAP_PRICE = 100.0;
    public static final double DEFAULT_MID_CAP_PRICE = 20.0;
    public static final double DEFAULT_SMALL_CAP_PRICE = 5.0;

    public static double getDefaultPriceWithCap(MarketCap marketCap) {
        return switch (marketCap) {
            case Mega -> DEFAULT_LARGE_CAP_PRICE + random.nextInt(-10, 10);
            case Large -> DEFAULT_LARGE_CAP_PRICE + random.nextInt(-10, 10);
            case Mid -> DEFAULT_MID_CAP_PRICE + random.nextInt(-5, 5);
            case Small -> DEFAULT_SMALL_CAP_PRICE + random.nextInt(-2, 2);
        };
    }

    public static double getDefaultPrice(MarketCap marketCap){
        return switch (marketCap) {
            case Mega -> DEFAULT_LARGE_CAP_PRICE + random.nextInt(-10, 10);
            case Large -> DEFAULT_LARGE_CAP_PRICE;
            case Mid -> DEFAULT_MID_CAP_PRICE;
            case Small -> DEFAULT_SMALL_CAP_PRICE;
        };
    }
}
