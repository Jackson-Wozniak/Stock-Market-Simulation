package org.api.stockmarket.stocks.stock.utils;

import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

public class PercentChange {

    public static double percentChange(double currentPrice, double lastDayPrice){
        //if(currentPrice == 0) return lastDayPrice * -1;
        return CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(
                (currentPrice - lastDayPrice) / lastDayPrice * 100);
    }
}
