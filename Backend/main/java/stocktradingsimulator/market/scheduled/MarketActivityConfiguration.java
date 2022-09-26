package stocktradingsimulator.market.scheduled;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class MarketActivityConfiguration {

    @Autowired
    private final HandleMarketActivity handleMarketActivity;

    private final Logger logger = LoggerFactory.getLogger(MarketActivityConfiguration.class);
    private static int marketHour = 0;
    private static int marketDay = 0;

    @Scheduled(fixedDelay = 1000L)//MarketIntervals.TEN_SECONDS)
    @SuppressWarnings("unused")
    public void dailyMarketActivity(){
        marketHour++;
        if(marketHour >= 24){
            logger.info("End of day " + handleMarketActivity.dailyMarketActivity());
            marketHour = 0;

            if(marketDay >= 30){
                handleMarketActivity.updateMarketMonthlyValues();
                marketDay = 0;
            }
            marketDay++;
        }else{
            //boolean value means that it is not the end of the day and only prices update
            handleMarketActivity.updateNewStockInformation(false);
        }
    }
}
