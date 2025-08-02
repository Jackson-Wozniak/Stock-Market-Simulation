package org.api.stockmarket.engine.scheduling;

import lombok.AllArgsConstructor;

import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.enums.CurrentTimeRange;
import org.api.stockmarket.engine.enums.MarketSimulatorMode;
import org.api.stockmarket.engine.enums.TemporalMarketMilestone;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.engine.service.MarketExecutorService;
import org.api.stockmarket.engine.service.MarketStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {
    private final MarketStateService marketStateService;
    private final MarketExecutorService marketExecutorService;
    private static final Logger logger = LoggerFactory.getLogger(MarketActivityScheduler.class);

    @Scheduled(fixedDelay = MarketEnvironmentProperties.MARKET_TIME_INTERVAL)
    @SuppressWarnings("unused")
    public void scheduledMarketActivity() {
        MarketState marketState = marketStateService.findMarketState();

        if(marketState.getCurrentTimeRange().equals(CurrentTimeRange.AFTER_HOURS)){
            marketState = marketStateService.incrementAfterHours();
            if(marketState.getCurrentTimeRange().equals(CurrentTimeRange.TRADING_HOURS)){
                logger.info("START OF DAY: {}", marketState);
            }
            return;
        }
        marketState = marketStateService.incrementAndGet();
        marketExecutorService.advanceMarket(marketState);
    }

}
