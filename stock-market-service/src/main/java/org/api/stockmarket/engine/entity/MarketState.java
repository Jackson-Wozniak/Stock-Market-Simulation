package org.api.stockmarket.engine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.engine.enums.MarketSentiment;
import org.api.stockmarket.engine.enums.TemporalMarketMilestone;

import java.time.ZonedDateTime;

@Getter
@Setter
public class MarketState {
    private final ZonedDateTime dateTime;
    private final MarketSentiment sentiment;
    private final TemporalMarketMilestone temporalMarketMilestone;

    public MarketState(ZonedDateTime dateTime, MarketSentiment sentiment){
        this.dateTime = dateTime;
        this.sentiment = sentiment;
        this.temporalMarketMilestone = TemporalMarketMilestone.of(dateTime);
    }

    @Override
    public String toString(){
        return new StringBuilder("\nCurrent Market Conditions: \n")
                .append("{\n    time : ")
                .append(dateTime)
                .append(",\n    sentiment : ")
                .append(sentiment.getName())
                .append("\n}")
                .toString();
    }
}
