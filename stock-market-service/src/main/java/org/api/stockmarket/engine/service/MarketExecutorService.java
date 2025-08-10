package org.api.stockmarket.engine.service;

import java.time.ZonedDateTime;

import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.modules.news.engines.NewsReleaseEngine;
import org.api.stockmarket.modules.stocks.service.PriceRecordService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

import static org.api.stockmarket.engine.enums.TemporalMarketMilestone.*;

@Component
@AllArgsConstructor
public class MarketExecutorService {

    private final StockService stockService;
    private final NewsReleaseEngine newsReleaseEngine;
    private final PriceRecordService priceRecordService;
    private static final Logger logger = LoggerFactory.getLogger(MarketExecutorService.class);

    public void advanceMarket(MarketState marketState){
        if(marketState.getTemporalMarketMilestone().equals(END_OF_DAY)){
            logger.info("END OF DAY: {}", marketState);
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
        stockService.runPriceChanges();
    }

    private void monthlyMarketActivity(ZonedDateTime date){

    }

    private void quarterlyMarketActivity(ZonedDateTime date){

    }
}
