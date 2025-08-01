package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum MarketCap {
    SMALL("Small", 1),
    MID("Mid", 2),
    LARGE("Large", 3),
    MEGA("Mega", 4),
    UNKNOWN("Unknown", -1);

    private final String name;
    private final int value;

    MarketCap(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static MarketCap fromValue(int value) {
        for (MarketCap style : MarketCap.values()) {
            if (style.value == value) {
                return style;
            }
        }
        return UNKNOWN;
    }

    public static MarketCap fromName(String name) {
        for (MarketCap style : MarketCap.values()) {
            if (style.name.equalsIgnoreCase(name)) {
                return style;
            }
        }
        return UNKNOWN;
    }
}
