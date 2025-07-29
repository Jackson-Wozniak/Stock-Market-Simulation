package org.api.stockmarket.modules.stocks.enums;

import java.util.Random;

public enum PriceVolatility {
    STABLE(0.2),
    LOW(0.35),
    NORMAL(0.5),
    HIGH(0.7),
    EXTREME(1.0);

    private final double magnitude;

    PriceVolatility(double val){
        this.magnitude = val;
    }

    /*
    Base magnitude is the floor of a change's standard deviation,
        increasing the value in the enums increases the noise of the
        price change, while a more moderate value allows for noise
        to play a role in the signal without directing it
     */
    public double applyMagnitude(double baseMagnitude){
        return baseMagnitude + magnitude;
    }
}
