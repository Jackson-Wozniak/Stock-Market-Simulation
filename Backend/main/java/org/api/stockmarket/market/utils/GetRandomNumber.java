package org.api.stockmarket.market.utils;

import org.api.stockmarket.stocks.stock.enums.MarketCap;

import java.util.Random;

public class GetRandomNumber {

    private static final Random random = new Random();

    public static double getRandomNumberForStocks(MarketCap marketCap){
        return switch (marketCap){
          case Large -> random.nextDouble(-.002, .002);
          case Mid -> random.nextDouble(-.001, .001);
          case Small -> random.nextDouble(-.003, .003);
          default -> -1;
        };
    }

    public static double getRandomPositiveNumberForStocks(MarketCap marketCap){
        return switch (marketCap){
            case Large -> random.nextDouble(0, .002);
            case Mid -> random.nextDouble(0, .001);
            case Small -> random.nextDouble(0, .003);
            default -> -1;
        };
    }

    public static int drawRandomNumberToThirty(){
        return random.nextInt(30);
    }
}
