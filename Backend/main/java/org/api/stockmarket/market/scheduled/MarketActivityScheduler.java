package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.market.constants.MarketIntervals;
import org.api.stockmarket.stocks.stock.service.StockHistoryService;
import org.api.tradinggame.account.service.AccountHistoryService;
import org.api.tradinggame.account.service.LimitOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private final StockHistoryService stockHistoryService;
    @Autowired
    private final IndexFundService indexFundService;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityScheduler.class);
    private static int marketHour = 0;
    private static int marketDay = 0;

    @Scheduled(fixedRate = 10L)
    @SuppressWarnings("unused")
    public void dailyMarketActivity() {
        marketHour++;
        limitOrderService.processAllLimitOrders();
        if (marketHour >= 24) {
            limitOrderService.truncateLimitOrders();
            accountHistoryService.saveDailyAccountHistory();
            stockHistoryService.saveStockHistoryDaily();
            indexFundService.updatePriceForAllFundsDaily();

            logger.info("New Day: " + handleMarketActivity.dailyMarketActivity());

            marketHour = 0;
            if (marketDay >= 30) {
                handleMarketActivity.updateMarketMonthlyValues(
                        accountHistoryService);
                marketDay = 0;
            }
            marketDay++;
        } else {
            //boolean value means that it is not the end of the day and only prices update
            handleMarketActivity.updateNewStockInformation(false);
        }
    }
}
