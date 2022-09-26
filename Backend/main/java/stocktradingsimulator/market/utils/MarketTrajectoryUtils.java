package stocktradingsimulator.market.utils;

import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.enums.MarketTrajectory;
import stocktradingsimulator.stocks.stock.model.entity.Stock;

import java.util.List;

public class MarketTrajectoryUtils {

    public static MarketTrajectory getNewMarketTrajectory(Market market, List<Stock> stockList){
        if(stockList == null || stockList.size() == 0) return MarketTrajectory.NORMAL;

        double stockPricesAverage = stockPricesAverage(stockList);
        double priceChange = (stockPricesAverage / market.getLastMonthAveragePrice()) * 100;
        if(priceChange >= 110){
           return MarketTrajectory.BULL;
        }
        if(priceChange <= 90){
            return MarketTrajectory.BEAR;
        }
        return MarketTrajectory.NORMAL;
    }

    public static double stockPricesAverage(List<Stock> stockList) {
        return Math.round((stockPricesSum(stockList) / stockList.size()) * 100.00) / 100.00 ;
    }

    public static double stockPricesSum(List<Stock> stockList){
        double priceSum = 0;
        for(Stock stock : stockList){
            priceSum += stock.getPrice();
        }
        return priceSum;
    }
}
