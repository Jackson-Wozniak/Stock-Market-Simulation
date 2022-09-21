package stocktradingsimulator.market.configuration;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.scheduled.HandleMarketActivity;
import stocktradingsimulator.stock.StockService;

@Configuration
@EnableScheduling
@AllArgsConstructor
@SuppressWarnings("unused")
public class MarketActivityConfiguration {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;
    @Autowired
    private final StockService stockService;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityConfiguration.class);
    private static int marketHour = 0;

    @Scheduled(fixedDelay = 10000L)
    public void marketActivity(){
        marketHour++;
        if(marketHour >= 24){
            logger.info("End of day");
            //boolean value confirms that it is the end of day
            handleMarketActivity.updateNewStockPrices(true);
            marketHour = 0;
            return;
        }
        handleMarketActivity.updateNewStockPrices(false);
    }
}
