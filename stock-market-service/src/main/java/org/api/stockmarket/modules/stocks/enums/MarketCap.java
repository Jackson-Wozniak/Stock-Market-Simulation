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

    public int defaultInnovationFactor(){
        return switch(this){
            case SMALL -> 10;
            case MID -> 5;
            case LARGE -> -2;
            case MEGA -> -5;
            case UNKNOWN -> 0;
        };
    }

    public int defaultTradingDemandFactor(){
        return switch(this){
            case SMALL -> -3;
            case MID -> -1;
            case LARGE -> 3;
            case MEGA -> 5;
            case UNKNOWN -> 0;
        };
    }

    public int defaultLiquidityFactor(){
        return switch(this){
            case SMALL -> -5;
            case MID -> -2;
            case LARGE -> 5;
            case MEGA -> 10;
            case UNKNOWN -> 0;
        };
    }

    public int defaultInvestorConfidenceFactor(){
        return switch(this){
            case SMALL -> -10;
            case MID -> -5;
            case LARGE -> 5;
            case MEGA -> 10;
            case UNKNOWN -> 0;
        };
    }
}
