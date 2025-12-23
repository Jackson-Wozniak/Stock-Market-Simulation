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
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Duration;
import java.time.Instant;

@Configuration
@EnableScheduling
@AllArgsConstructor
@Profile("!test")
public class MarketActivityScheduler {
    private final MarketStateService marketStateService;
    private final MarketExecutorService marketExecutorService;
    private static final Logger logger = LoggerFactory.getLogger(MarketActivityScheduler.class);
    private static final Logger performanceLogger = LoggerFactory.getLogger("performanceLogger");

    @Scheduled(fixedDelay = MarketEnvironmentProperties.MARKET_TIME_INTERVAL)
    @SuppressWarnings("unused")
    public void scheduledMarketActivity() {
        Instant start, end;
        if(MarketEnvironmentProperties.PERFORMANCE_DEBUG){
            start = Instant.now();
        }

        MarketState marketState = marketStateService.findMarketState();

        if(marketState.getCurrentTimeRange().equals(CurrentTimeRange.AFTER_HOURS)){
            marketState = marketStateService.incrementAfterHours();
            if(marketState.getCurrentTimeRange().equals(CurrentTimeRange.TRADING_HOURS)){
                logger.info("START OF DAY: {}", marketState);
            }
            if(MarketEnvironmentProperties.PERFORMANCE_DEBUG){
                end = Instant.now();
                printPerformanceLog(start, end);
            }
            return;
        }
        marketState = marketStateService.incrementAndGet();
        marketExecutorService.advanceMarket(marketState);
        if(MarketEnvironmentProperties.PERFORMANCE_DEBUG){
            end = Instant.now();
            printPerformanceLog(start, end);
        }
    }

    private static void printPerformanceLog(Instant start, Instant end){
        long millis = Duration.between(start, end).toMillis();
        performanceLogger.info("{}ms", millis);
    }

}
