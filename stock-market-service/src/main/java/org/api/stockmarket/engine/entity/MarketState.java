package org.api.stockmarket.engine.entity;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.engine.enums.CurrentTimeRange;
import org.api.stockmarket.engine.enums.MarketSentiment;
import org.api.stockmarket.engine.enums.TemporalMarketMilestone;

import java.time.ZonedDateTime;

@Getter
@Setter
public class MarketState {
    private final ZonedDateTime dateTime;
    private final MarketSentiment sentiment;
    private final TemporalMarketMilestone temporalMarketMilestone;

    public MarketState(ZonedDateTime dateTime, MarketSentiment sentiment,
                       TemporalMarketMilestone marketMilestone){
        this.dateTime = dateTime;
        this.sentiment = sentiment;
        this.temporalMarketMilestone = marketMilestone;
    }

    public CurrentTimeRange getCurrentTimeRange(){
        return ((dateTime.getHour() >= 17) ||
                (dateTime.getHour() < 9)) ? CurrentTimeRange.AFTER_HOURS : CurrentTimeRange.TRADING_HOURS;
    }

    @Override
    public String toString(){
        return new StringBuilder("\nCurrent Market Conditions: \n")
                .append("{\n    time : ")
                .append(dateTime)
                .append(",\n    sentiment : ")
                .append(sentiment.getName())
                .append(",\n    status : ")
                .append(getCurrentTimeRange().toString())
                .append("\n}")
                .toString();
    }
}
