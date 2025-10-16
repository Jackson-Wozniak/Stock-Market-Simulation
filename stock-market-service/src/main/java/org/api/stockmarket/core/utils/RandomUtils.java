package org.api.stockmarket.core.utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static <T> T getRandomElement(List<T> elements){
        return elements.get(random.nextInt(0, elements.size() - 1));
    }

    public static int getRandomInt(int min, int maxInclusive){
        return random.nextInt(min, maxInclusive + 1);
    }
}
