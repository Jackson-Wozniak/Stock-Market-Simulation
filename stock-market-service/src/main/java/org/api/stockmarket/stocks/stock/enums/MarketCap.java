package org.api.stockmarket.stocks.stock.enums;

public enum MarketCap {
    Small,
    Mid,
    Large;

    public static MarketCap map(String str){
        return switch (str.toLowerCase()){
            case "small" -> Small;
            case "mid" -> Mid;
            case "large" -> Large;
            default -> null;
        };
    }
}
