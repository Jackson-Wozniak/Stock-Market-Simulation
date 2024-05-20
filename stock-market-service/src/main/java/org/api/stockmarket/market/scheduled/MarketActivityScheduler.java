package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;

import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.constants.MarketIntervals;
import org.api.stockmarket.market.enums.TimeStamp;
import org.api.stockmarket.market.service.MarketService;
import org.api.stockmarket.stocks.stock.service.StockPriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {

    private final MarketManager marketManager;
    private final MarketService marketService;

    @Scheduled(fixedRate = MarketIntervals.ONE_SECOND)
    @SuppressWarnings("unused")
    public void dailyMarketActivity() {
        TimeStamp time = marketService.incrementAndSave();

        if(time.equals(TimeStamp.EndOfDay)){
            marketManager.dailyMarketActivity();
            return;
        }
        if(time.equals(TimeStamp.EndOfMonth)){
            marketManager.monthlyMarketActivity();
            return;
        }
        marketManager.hourlyMarketActivity();
    }

}
