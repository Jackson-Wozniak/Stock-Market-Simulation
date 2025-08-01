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
}
