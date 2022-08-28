package stocktradingsimulator.market;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GetRandomNumber {

    private static final Random random = new Random();

    public double getRandomNumberForLargeCap(){
        return random.nextDouble(-.002, .002);
    }

    public double getRandomPositiveNumberForLargeCap(){
        return random.nextDouble(0, .002);
    }

    public double getRandomNumberForMidCap(){
        return random.nextDouble(-.001, .001);
    }

    public double getRandomPositiveNumberForMidCap(){
        return random.nextDouble(0, .001);
    }

    public double getRandomNumberForSmallCap(){
        return random.nextDouble(-.003, .003);
    }

    public double getRandomPositiveNumberForSmallCap(){
        return random.nextDouble(0, .003);
    }
}
