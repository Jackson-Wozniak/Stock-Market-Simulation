package org.api.stockmarket.engine.scheduling;

import lombok.AllArgsConstructor;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.engine.service.MarketExecutorService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityScheduler {

    private final MarketExecutorService marketExecutorService;

    @Scheduled(fixedRate = MarketEnvironmentProperties.MARKET_TIME_INTERVAL)
    @SuppressWarnings("unused")
    public void scheduledMarketActivity() {
        if(MarketEnvironmentProperties.MARKET_MODE.equals(MarketSimulatorMode.SIMULATOR_ONLY)){
            return; //testing mode
        }
        marketExecutorService.advanceMarket();
    }

}
