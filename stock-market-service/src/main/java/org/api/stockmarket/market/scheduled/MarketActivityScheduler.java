package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;

import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.constants.MarketIntervals;
import org.api.stockmarket.market.enums.TimeStamp;
import org.api.stockmarket.market.utils.DateConversion;
import org.api.stockmarket.stocks.stock.service.StockPriceHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;
    @Autowired
    private final StockPriceHistoryService stockPriceHistoryService;
    @Autowired
    private final IndexFundService indexFundService;

    @Scheduled(fixedRate = MarketIntervals.ONE_SECOND)
    @SuppressWarnings("unused")
    public void dailyMarketActivity() {
        TimeStamp time = handleMarketActivity.incrementMarket();

        if(time.equals(TimeStamp.EndOfDay)){
            stockPriceHistoryService.saveStockHistoryDaily();
            indexFundService.updatePriceForAllFundsDaily();
            handleMarketActivity.dailyMarketActivity();
            return;
        }
        if(time.equals(TimeStamp.EndOfMonth)){
            handleMarketActivity.dailyMarketActivity();
            handleMarketActivity.updateMarketMonthlyValues();
            return;
        }
        handleMarketActivity.updateNewStockInformation(false);
    }

}
