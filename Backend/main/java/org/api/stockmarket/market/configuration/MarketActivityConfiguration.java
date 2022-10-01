package org.api.stockmarket.market.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.constants.MarketIntervals;
import org.api.stockmarket.market.scheduled.HandleMarketActivity;
import org.api.stockmarket.stocks.stock.service.StockHistoryService;
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
public class MarketActivityConfiguration {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;
    @Autowired
    private final LimitOrderService limitOrderService;
    @Autowired
    private final AccountHistoryService accountHistoryService;
    @Autowired
    private final StockHistoryService stockHistoryService;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityConfiguration.class);
    private static int marketHour = 0;
    private static int marketDay = 0;

    @Scheduled(fixedDelay = MarketIntervals.TEN_SECONDS)
    @SuppressWarnings("unused")
    public void dailyMarketActivity() {
        marketHour++;
        limitOrderService.processAllLimitOrders();
        if (marketHour >= 24) {
            logger.info("End of day " + handleMarketActivity.dailyMarketActivity());

            limitOrderService.truncateLimitOrders();
            accountHistoryService.saveDailyAccountHistory();
            stockHistoryService.saveStockHistoryDaily();

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
