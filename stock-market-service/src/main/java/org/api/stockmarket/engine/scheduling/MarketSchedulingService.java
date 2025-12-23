package org.api.stockmarket.engine.scheduling;

import lombok.AllArgsConstructor;
import org.api.stockmarket.core.exception.BadRequestException;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarketSchedulingService {
    private final MarketActivityScheduler marketActivityScheduler;

    public void updateInterval(long millis){
        if(millis < 100){
            throw new BadRequestException("Intervals must be 1 second or more");
        }
        marketActivityScheduler.schedule(millis);
    }

    public void pause(){
        marketActivityScheduler.stop();
    }

    public void resume(long millis){
        marketActivityScheduler.schedule(millis);
    }

    public void resume(){
        marketActivityScheduler.schedule(MarketEnvironmentProperties.MARKET_TIME_INTERVAL);
    }
}
