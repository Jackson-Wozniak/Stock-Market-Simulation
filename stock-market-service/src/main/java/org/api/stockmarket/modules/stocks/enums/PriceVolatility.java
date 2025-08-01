package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum PriceVolatility {
    STABLE("Stable", 0.2),
    LOW("Low", 0.35),
    NORMAL("Normal", 0.5),
    HIGH("High", 0.7),
    EXTREME("Extreme", 1.0),
    UNKNOWN("Unknown", 0.0);

    private final String name;
    private final double magnitude;

    PriceVolatility(String name, double val){
        this.name = name;
        this.magnitude = val;
    }

    public static PriceVolatility fromName(String name) {
        for (PriceVolatility style : PriceVolatility.values()) {
            if (style.name.equalsIgnoreCase(name)) {
                return style;
            }
        }
        return UNKNOWN;
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
