package org.api.stockmarket.market.scheduled;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.enums.TimeStamp;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.market.utils.MarketTrajectoryUtils;
import org.api.stockmarket.stocks.earnings.helpers.ReleaseEarningsReport;
import org.api.stockmarket.stocks.news.helpers.RandomNewsEvents;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockPriceHistoryService;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MarketManager {

    private final StockService stockService;
    private final MarketService marketService;
    private final RandomNewsEvents randomNewsEvents;
    private final ReleaseEarningsReport releaseEarningsReport;
    private final StockPriceHistoryService stockPriceHistoryService;
    private final IndexFundService indexFundService;

    public long advanceMarket(){
        TimeStamp time = marketService.incrementAndSave();

        Instant start = Instant.now();
        if(time.equals(TimeStamp.EndOfDay)){
            dailyMarketActivity();
            return Duration.between(start, Instant.now()).toMillis();
        }
        if(time.equals(TimeStamp.EndOfMonth)){
            monthlyMarketActivity();
            return Duration.between(start, Instant.now()).toMillis();
        }
        hourlyMarketActivity();
        return Duration.between(start, Instant.now()).toMillis();
    }

    private void dailyMarketActivity() {
        stockPriceHistoryService.saveStockHistoryDaily();
        indexFundService.updatePriceForAllFundsDaily();

        hourlyMarketActivity();

        Market market = marketService.findMarketEntity();
        randomNewsEvents.newsRunner(market.getDate());
        if (market.isEndOfQuarter()) {
            releaseEarningsReport.handleQuarterlyEarningsReports(
                    stockService.getAllStocks(), market.getDate());
        }
    }

    private void hourlyMarketActivity(){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(Stock::updatePrice);
        stockService.updateAllStocksInDatabase(stocks);
    }

    private void monthlyMarketActivity(){
        dailyMarketActivity();
        updateMarketMonthlyValues();
    }

    private void updateMarketMonthlyValues() {
        Market market = marketService.findMarketEntity();
        market.setMarketTrajectory(MarketTrajectoryUtils.getNewMarketTrajectory(
                market, stockService.getAllStocks()));
        market.setLastMonthAveragePrice(MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocks()));
        marketService.saveMarketEntity(market);

        // all daily account records will be removed at the end of each year, creating a clean slate
        if (market.isEndOfYear()) {
            stockPriceHistoryService.truncateStockHistoryAtEndOfYear();
        }
    }
}
