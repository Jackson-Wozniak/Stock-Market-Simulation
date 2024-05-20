package org.api.stockmarket.stocks.stock.enums;

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
}
