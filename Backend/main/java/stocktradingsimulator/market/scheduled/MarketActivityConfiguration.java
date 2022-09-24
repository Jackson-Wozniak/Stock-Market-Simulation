package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import stocktradingsimulator.market.constants.MarketIntervals;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityConfiguration {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityConfiguration.class);
    private static int marketHour = 0;
    private static int marketDay = 0;

    @Scheduled(fixedDelay = MarketIntervals.TEN_SECONDS)
    @SuppressWarnings("unused")
    public void dailyMarketActivity(){
        marketHour++;
        if(marketHour >= 24){
            //boolean value confirms that it is the end of day
            handleMarketActivity.updateNewStockPrices(true);
            String date = handleMarketActivity.incrementMarketDay();
            handleMarketActivity.createRandomNewsEvents();
            logger.info("End of day " + date);
            marketHour = 0;

            if(marketDay >= 30){
                logger.info("End of month");
                handleMarketActivity.updateMarketMonthlyValues();
                marketDay = 0;
            }
            marketDay++;
            return;
        }
        handleMarketActivity.updateNewStockPrices(false);
    }
}
