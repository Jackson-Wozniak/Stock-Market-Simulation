package stocktradingsimulator.market;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GetRandomNumber {

    private final Random random = new Random();

    public double getRandomNumberForLargeCap(){
        return random.nextDouble(-.002, .002);
    }

    public double getRandomPositiveNumberForLargeCap(){
        return random.nextDouble(0, .002);
    }
}
