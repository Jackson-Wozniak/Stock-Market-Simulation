package org.api.stockmarket.market.scheduled;

import java.util.List;
import java.util.Random;

import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.entity.Market;
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
    private static final Random random = new Random();

    public void dailyMarketActivity() {
        stockPriceHistoryService.saveStockHistoryDaily();
        indexFundService.updatePriceForAllFundsDaily();

        hourlyMarketActivity();
        createRandomNewsEvents();

        Market market = marketService.findMarketEntity();
        if (market.isEndOfQuarter()) {
            releaseEarningsReport.handleQuarterlyEarningsReports(
                    stockService.getAllStocks(), market.getDate());
        }
    }

    public void hourlyMarketActivity(){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(Stock::updatePrice);
        stockService.updateAllStocksInDatabase(stocks);
    }

    public void monthlyMarketActivity(){
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

    private void createRandomNewsEvents() {
        int randomNumber = random.nextInt(30);
        if (randomNumber == 10) {
            randomNewsEvents.processPositiveNewsEvent(marketService.findMarketEntity().getDate());
            System.out.println("Positive News");
        } else if (randomNumber == 20) {
            randomNewsEvents.processNegativeNewsEvents(marketService.findMarketEntity().getDate());
            System.out.println("Negative News");
        }
    }
}
