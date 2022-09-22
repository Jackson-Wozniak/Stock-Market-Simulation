package stocktradingsimulator.market.utils;

import java.util.Random;

public class GetRandomNumber {

    private static final Random random = new Random();

    public static double getRandomNumberForStocks(String marketCap){
        return switch (marketCap.toLowerCase()){
          case "large" -> random.nextDouble(-.002, .002);
          case "mid" -> random.nextDouble(-.001, .001);
          case "small" -> random.nextDouble(-.003, .003);
          default -> -1;
        };
    }

    public static double getRandomPositiveNumberForStocks(String marketCap){
        return switch (marketCap.toLowerCase()){
            case "large" -> random.nextDouble(0, .002);
            case "mid" -> random.nextDouble(0, .001);
            case "small" -> random.nextDouble(0, .003);
            default -> -1;
        };
    }
}
