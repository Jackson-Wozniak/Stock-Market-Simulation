package org.api.stockmarket.modules.stocks.enums;

public enum MarketCap {
    Small,
    Mid,
    Large,
    Mega;

    public static MarketCap map(String str){
        return switch (str.toLowerCase()){
            case "small" -> Small;
            case "mid" -> Mid;
            case "large" -> Large;
            case "mega" -> Mega;
            default -> null;
        };
    }
}
