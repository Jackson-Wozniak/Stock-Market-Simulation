package org.api.stockmarket.market.configuration;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class MarketConfiguration {

    @Autowired
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
