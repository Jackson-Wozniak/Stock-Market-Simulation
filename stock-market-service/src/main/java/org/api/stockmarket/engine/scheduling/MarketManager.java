package org.api.stockmarket.engine.scheduling;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.api.stockmarket.engine.entity.Market;
import org.api.stockmarket.engine.enums.TimeStamp;
import org.api.stockmarket.engine.service.MarketService;
import org.api.stockmarket.engine.utils.MarketTrajectoryUtils;
import org.api.stockmarket.modules.news.service.EarningsManager;
import org.api.stockmarket.modules.news.service.NewsManager;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.StockPriceHistoryService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MarketManager {

    private final StockService stockService;
    private final MarketService marketService;
    private final NewsManager newsManager;
    private final EarningsManager earningsManager;
    private final StockPriceHistoryService stockPriceHistoryService;

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

        hourlyMarketActivity();

        Market market = marketService.findMarketEntity();

        newsManager.runDailyNewsStories(stockService.getAllStocks(), market.getDate());

        if (market.isEndOfQuarter()) {
            earningsManager.handleQuarterlyEarningsReports(
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
