package io.github.stockmarket.engine.service;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MarketExecutorService {

//    private final StockService stockService;
//    private final NewsReleaseEngine newsReleaseEngine;
//    private final PriceRecordService priceRecordService;
//    private static final Logger logger = LoggerFactory.getLogger(MarketExecutorService.class);
//
//    public void advanceMarket(MarketState marketState){
//        if(marketState.getTemporalMarketMilestone().equals(END_OF_DAY)){
//            logger.info("END OF DAY: {}", marketState);
//            executeEndOfDayTick(marketState.getDateTime());
//        }
//        executeIntraDayTick();
//    }
//
//    private void executeIntraDayTick() {
//        stockService.runPriceChanges();
//    }
//
//    private void executeEndOfDayTick(ZonedDateTime date) {
//        priceRecordService.savePricesEOD(stockService.getAllStocks(), date);
//        priceRecordService.archiveRecordsAtOrBeforeDate(date.minusDays(7));
//
//        List<NewsRelease> stories = newsReleaseEngine.executeNewsCycle(date);
//
//        stockService.updatePricingAttributesAfterNews(stories);
//    }
}
