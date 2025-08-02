package org.api.stockmarket.engine.service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.modules.news.engines.EarningsEngine;
import org.api.stockmarket.modules.news.engines.NewsEngine;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.PriceRecordService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import static org.api.stockmarket.engine.enums.TemporalMarketMilestone.*;

@Component
@AllArgsConstructor
public class MarketExecutorService {

    private final StockService stockService;
    private final MarketStateService marketStateService;
    private final NewsEngine newsEngine;
    private final EarningsEngine earningsEngine;
    private final PriceRecordService priceRecordService;

    public void advanceMarket(){
        MarketState marketState = marketStateService.incrementAndSave();

        if(marketState.getTemporalMarketMilestone().equals(END_OF_DAY)){
            dailyMarketActivity(marketState.getDateTime());
        }
        if(marketState.getTemporalMarketMilestone().equals(END_OF_MONTH)){
            monthlyMarketActivity(marketState.getDateTime());
        }
        if(marketState.getTemporalMarketMilestone().equals(END_OF_QUARTER)){
            quarterlyMarketActivity(marketState.getDateTime());
        }
        hourlyMarketActivity();
    }

    private void dailyMarketActivity(ZonedDateTime date) {
        priceRecordService.savePricesEOD(stockService.getAllStocks(), date);

        //newsEngine.runDailyNewsStories(stockService.getAllStocks(), date);
    }

    private void hourlyMarketActivity(){
        List<Stock> stocks = stockService.getAllStocks();
        stocks.forEach(Stock::runPriceChange);
        stockService.updateAllStocksInDatabase(stocks);
    }

    private void monthlyMarketActivity(ZonedDateTime date){

    }

    private void quarterlyMarketActivity(ZonedDateTime date){

    }
}
