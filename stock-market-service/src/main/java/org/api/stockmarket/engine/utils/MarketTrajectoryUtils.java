package org.api.stockmarket.engine.utils;

import org.api.stockmarket.modules.stocks.entity.Stock;

import java.util.List;

public class MarketTrajectoryUtils {

    public static double stockPricesAverage(List<Stock> stockList) {
        return Math.round((stockPricesSum(stockList) / stockList.size()) * 100.00) / 100.00;
    }

    public static double stockPricesSum(List<Stock> stockList) {
        double priceSum = 0;
        for (Stock stock : stockList) {
            priceSum += stock.getPrice();
        }
        return priceSum;
    }
}
