package org.api.stockmarket.modules.stocks.enums;

import lombok.Getter;

@Getter
public enum InvestmentStyle {
    BLUE_CHIP("Blue Chip"),
    GROWTH("Growth"),
    VALUE("Value"),
    MEME("Meme"),
    DIVIDEND("Dividend"),
    BALANCED("Balanced");

    private final String name;

    InvestmentStyle(String name){
        this.name = name;
    }
}
