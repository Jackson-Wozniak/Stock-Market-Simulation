package org.api.stockmarket.engine.scheduling;

import lombok.AllArgsConstructor;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.engine.properties.MarketIntervals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {

    private final MarketManager marketManager;
    private static final Logger stockLogger = LoggerFactory.getLogger("stockLogger");

    @Scheduled(fixedRate = MarketIntervals.ONE_SECOND)
    @SuppressWarnings("unused")
    public void scheduledMarketActivity() {
        if(MarketEnvironmentProperties.MARKET_MODE.equals(MarketSimulatorMode.SIMULATOR_ONLY)){
            return; //testing mode
        }

        long millis = marketManager.advanceMarket();
        stockLogger.info("{}ms", millis);
    }

}
