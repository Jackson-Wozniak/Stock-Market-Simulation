package org.api.stockmarket.stocks.stock.utils;

import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

public class PercentChange {

    public static double percentChange(double currentPrice, double lastDayPrice) {
        if(lastDayPrice == 0) return currentPrice;
        return CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(
                (currentPrice - lastDayPrice) / lastDayPrice * 100);
    }
}
