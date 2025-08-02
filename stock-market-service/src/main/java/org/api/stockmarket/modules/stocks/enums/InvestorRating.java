package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum InvestorRating {
    SELL("Sell", 1),
    HOLD("Hold", 2),
    BUY("Buy", 3),
    STRONG_BUY("Strong Buy", 4),
    NEUTRAL("Neutral", 5),
    UNKNOWN("Unknown", -1);

    private final String name;
    private final int value;

    InvestorRating(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static InvestorRating fromValue(int value) {
        for (InvestorRating style : InvestorRating.values()) {
            if (style.value == value) {
                return style;
            }
        }
        return UNKNOWN;
    }

    public static InvestorRating fromName(String name) {
        for (InvestorRating style : InvestorRating.values()) {
            if (style.name.equalsIgnoreCase(name)) {
                return style;
            }
        }
        return UNKNOWN;
    }

    public int defaultInnovationFactor(){
        return switch(this){
            case SELL -> -20;
            case HOLD -> -6;
            case NEUTRAL -> 0;
            case BUY -> 9;
            case STRONG_BUY -> 15;
            case UNKNOWN -> 0;
        };
    }

    public int defaultInvestorConfidenceFactor(){
        return switch(this){
            case SELL -> -25;
            case HOLD -> -12;
            case NEUTRAL, UNKNOWN -> 0;
            case BUY -> 10;
            case STRONG_BUY -> 15;
        };
    }

    public int defaultTradingDemandFactor(){
        return switch(this){
            case SELL -> -20;
            case HOLD -> -6;
            case NEUTRAL, UNKNOWN -> 0;
            case BUY -> 9;
            case STRONG_BUY -> 15;
        };
    }

    public int defaultLiquidityFactor(){
        return switch(this){
            case SELL -> -8;
            case HOLD -> -4;
            case NEUTRAL, UNKNOWN -> 0;
            case BUY -> 8;
            case STRONG_BUY -> 20;
        };
    }
}
