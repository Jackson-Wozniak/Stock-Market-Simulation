package org.api.stockmarket.modules.stocks.enums;

public enum InvestorRating {
    Sell,
    Hold,
    Neutral,
    Buy,
    StrongBuy;

    public int investorRatingMultiplier(){
        return switch (this){
            case Sell -> -2;
            case Hold -> -1;
            case Neutral -> 0;
            case Buy -> 1;
            case StrongBuy -> 2;
        };
    }

    public static InvestorRating map(String str){
        return switch (str.toLowerCase()){
            case "buy" -> Buy;
            case "sell" -> Sell;
            case "hold" -> Hold;
            case "neutral" -> Neutral;
            case "strongbuy" -> StrongBuy;
            default -> null;
        };
    }
}
