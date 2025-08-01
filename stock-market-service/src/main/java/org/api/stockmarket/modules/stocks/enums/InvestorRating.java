package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum InvestorRating {
    SELL("Sell", 1),
    HOLD("Hold", 2),
    BUY("Buy", 3),
    STRONG_BUY("Strong Buy", 4),
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
}
