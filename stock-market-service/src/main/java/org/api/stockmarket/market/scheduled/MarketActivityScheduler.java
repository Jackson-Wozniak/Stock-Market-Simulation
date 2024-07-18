package org.api.stockmarket.market.scheduled;

import lombok.AllArgsConstructor;

import org.api.stockmarket.market.properties.MarketIntervals;
import org.api.stockmarket.market.enums.TimeStamp;
import org.api.stockmarket.market.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.Instant;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {

    private final MarketManager marketManager;
    private static final Logger stockLogger = LoggerFactory.getLogger("stockLogger");

    @Scheduled(fixedRate = MarketIntervals.ONE_SECOND)
    @SuppressWarnings("unused")
    public void scheduledMarketActivity() {
        long millis = marketManager.advanceMarket();
        stockLogger.info("{}ms", millis);
    }

}
