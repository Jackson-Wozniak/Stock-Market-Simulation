package org.api.stockmarket.engine.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.service.MarketStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class MarketConfiguration {

    private final MarketStateService marketStateService;
    private final Logger logger = LoggerFactory.getLogger(MarketConfiguration.class);

    @PostConstruct
    public void configureBaselineMarket() {
        MarketState state = marketStateService.findMarketState();
        logger.info(state.toString());
    }
}
