package org.api.stockmarket.engine.scheduling;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.enums.CurrentTimeRange;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.engine.service.MarketExecutorService;
import org.api.stockmarket.engine.service.MarketStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ScheduledFuture;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class MarketActivityScheduler {
    private final MarketStateService marketStateService;
    private final MarketExecutorService marketExecutorService;
    private final TaskScheduler taskScheduler;

    private ScheduledFuture<?> future;
    private boolean running = false;
    private long intervalMs = MarketEnvironmentProperties.MARKET_TIME_INTERVAL;

    private static final Logger logger = LoggerFactory.getLogger(MarketActivityScheduler.class);
    private static final Logger performanceLogger = LoggerFactory.getLogger("performanceLogger");

    @PostConstruct
    public void start(){
        schedule();
    }

    public void updateInterval(long intervalMs){
        this.intervalMs = intervalMs;
        if(running){
            schedule();
        }else{
            marketStateService.updateSchedulingConfig(false, intervalMs);
        }
    }

    public void schedule() {
        stop();
        future = taskScheduler.scheduleWithFixedDelay(
                this::scheduledMarketActivity, Duration.ofMillis(intervalMs)
        );
        running = true;
        marketStateService.updateSchedulingConfig(true, intervalMs);
    }

    public void stop() {
        if(!running) return;
        future.cancel(false);
        running = false;
        marketStateService.updateSchedulingConfig(false, this.intervalMs);
    }

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
