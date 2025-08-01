package org.api.stockmarket.engine.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.entity.Market;
import org.api.stockmarket.engine.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class MarketConfiguration {

    private final MarketService marketService;
    private final Logger logger = LoggerFactory.getLogger(MarketConfiguration.class);

    @PostConstruct
    public void configureBaselineMarket() {
        //Calling this method will automatically create a new Market Entity if one doesn't exist
        //This happens because only a single market entity should exist, with ID 1
        Market market = marketService.findMarketEntity();
        logger.info("Current Market Conditions: " + market.toString());
    }
}
