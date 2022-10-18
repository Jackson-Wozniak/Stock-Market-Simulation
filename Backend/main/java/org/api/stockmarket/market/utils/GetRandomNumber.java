package org.api.stockmarket.market.utils;

import org.api.stockmarket.stocks.stock.enums.MarketCap;

import java.util.Random;

public class GetRandomNumber {

    private static final Random random = new Random();

    public static double getRandomNumberForStocks(MarketCap marketCap) {
        return switch (marketCap) {
            case Large -> random.nextDouble(-.002, .002);
            case Mid -> random.nextDouble(-.001, .001);
            case Small -> random.nextDouble(-.003, .003);
        };
    }

    public static double getRandomSmallNumber() {
        return random.nextDouble(-.001, .001);
    }

    public static double getRandomSmallPositiveNumber() {
        return random.nextDouble(0, .0002);
    }

    public static double getRandomPositiveNumberForStocks(MarketCap marketCap) {
        return switch (marketCap) {
            case Large -> random.nextDouble(0, .0018);
            case Mid -> random.nextDouble(0, .0008);
            case Small -> random.nextDouble(0, .00028);
        };
    }

    public static int drawRandomNumberToThirty() {
        return random.nextInt(30);
    }
}
