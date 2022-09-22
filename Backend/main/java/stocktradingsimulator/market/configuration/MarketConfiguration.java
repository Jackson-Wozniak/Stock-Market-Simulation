package stocktradingsimulator.market.configuration;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import stocktradingsimulator.account.model.entity.StockInventory;
import stocktradingsimulator.account.service.StockInventoryService;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.service.MarketService;
import stocktradingsimulator.stock.service.NewsService;
import stocktradingsimulator.stock.service.StockService;

import javax.annotation.PostConstruct;

@Configuration
@AllArgsConstructor
public class MarketConfiguration {

    @Autowired
    private final MarketService marketService;
    private final Logger logger = LoggerFactory.getLogger(MarketConfiguration.class);

    @PostConstruct
    public void configureBaselineMarket(){
        //Calling this method will automatically create a new Market Entity if one doesn't exist
        //This happens because only a single market entity should exist, with ID 1
        Market market = marketService.findMarketEntity();
        logger.info("Current Market Conditions: " + market.toString());
    }
}
