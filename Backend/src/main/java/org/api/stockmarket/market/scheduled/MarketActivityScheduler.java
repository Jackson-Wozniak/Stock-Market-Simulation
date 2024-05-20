package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.constants.MarketIntervals;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.utils.DateConversion;
import org.api.stockmarket.stocks.stock.service.StockPriceHistoryService;
import org.api.tradinggame.account.service.AccountHistoryService;
import org.api.tradinggame.account.service.LimitOrderService;
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
    private final LimitOrderService limitOrderService;
    @Autowired
    private final AccountHistoryService accountHistoryService;
    @Autowired
    private final StockPriceHistoryService stockPriceHistoryService;
    @Autowired
    private final IndexFundService indexFundService;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityScheduler.class);


    @Scheduled(fixedRate = MarketIntervals.ONE_SECOND)
    @SuppressWarnings("unused")
    public void dailyMarketActivity() {
        
        // dev note: the function here feels like it should move into the 
        // market itself - so the market has responsibility for it's own
        // logic on timekeeping. This class is still needed however
        // to provide the 'tick' impoteus

        // "tick" move forward the time in the market
        var now = handleMarketActivity.incrementMarket();
        limitOrderService.processAllLimitOrders();
        logger.info("Market Time: " + now);

        // check if this is the start of the day i.e. 00 h
        // need to the daily processing
        if (now.getHour()==0){
            limitOrderService.truncateLimitOrders();
            accountHistoryService.saveDailyAccountHistory();
            stockPriceHistoryService.saveStockHistoryDaily();
            indexFundService.updatePriceForAllFundsDaily();

            logger.info("New Day: " + handleMarketActivity.dailyMarketActivity());

            // if as well as being the start of the day, it 'today' is 
            // the last day of the month other processing needs to take place    
            if (DateConversion.isLastDayMonth(now)){
                handleMarketActivity.updateMarketMonthlyValues(
                        accountHistoryService);
            }
        } else {
            // boolean value means that it is not the end of the day and only prices update
            handleMarketActivity.updateNewStockInformation(false);
        }
    }

}
