package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum InvestmentStyle {
    BLUE_CHIP("Blue Chip", 1),
    GROWTH("Growth", 2),
    VALUE("Value", 3),
    MEME("Meme", 4),
    DIVIDEND("Dividend", 5),
    BALANCED("Balanced", 6),
    UNKNOWN("Unknown", -1);

    private final String name;
    private final int value;

    InvestmentStyle(String name, int value){
        this.name = name;
        this.value = value;
    }

    public static InvestmentStyle fromValue(int value) {
        for (InvestmentStyle style : InvestmentStyle.values()) {
            if (style.value == value) {
                return style;
            }
        }
        return UNKNOWN;
    }

    public static InvestmentStyle fromName(String name) {
        for (InvestmentStyle style : InvestmentStyle.values()) {
            if (style.name.equalsIgnoreCase(name)) {
                return style;
            }
        }
        return UNKNOWN;
    }

    public int defaultInnovationFactor(){
        return switch(this){
            case BLUE_CHIP -> 5;
            case GROWTH -> 15;
            case VALUE -> -10;
            case MEME -> -5;
            case DIVIDEND -> -15;
            case BALANCED -> 2;
            case UNKNOWN -> 0;
        };
    }

    public int defaultLiquidityFactor(){
        return switch(this){
            case BLUE_CHIP -> 5;
            case GROWTH -> 15;
            case VALUE -> 10;
            case MEME -> -15;
            case DIVIDEND -> -12;
            case BALANCED -> 4;
            case UNKNOWN -> 0;
        };
    }

    public int defaultTradingDemandFactor(){
        return switch(this){
            case BLUE_CHIP -> 5;
            case GROWTH -> 15;
            case VALUE -> -5;
            case MEME -> 20;
            case DIVIDEND -> -20;
            case BALANCED -> -15;
            case UNKNOWN -> 0;
        };
    }

    public int defaultInvestorConfidenceFactor(){
        return switch(this){
            case BLUE_CHIP -> 15;
            case GROWTH -> 12;
            case VALUE -> 10;
            case MEME -> -20;
            case DIVIDEND -> 22;
            case BALANCED -> 5;
            case UNKNOWN -> 0;
        };
    }
}
