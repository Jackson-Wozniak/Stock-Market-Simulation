package org.api.stockmarket.engine.service;

import java.time.ZonedDateTime;
import java.util.List;

import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.stocks.service.PriceRecordService;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.api.stockmarket.modules.news.engines.*;
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
            executeEndOfDayTick(marketState.getDateTime());
        }
        executeIntraDayTick();
    }

    private void executeIntraDayTick() {
        stockService.runPriceChanges();
    }

    private void executeEndOfDayTick(ZonedDateTime date) {
        priceRecordService.savePricesEOD(stockService.getAllStocks(), date);
        priceRecordService.archiveRecordsAtOrBeforeDate(date.minusDays(7));

        List<NewsRelease> stories = newsReleaseEngine.executeNewsCycle(date);

        stockService.updatePricingAttributesAfterNews(stories);
    }
}
