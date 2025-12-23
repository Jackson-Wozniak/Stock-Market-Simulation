package org.api.stockmarket.engine.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.core.utils.DateConversion;
import org.api.stockmarket.engine.entity.MarketState;

@Getter
@Setter
public class MarketStateDTO {
    private String date;
    private boolean isRunning;
    private long currentIntervalMs;
    private String trajectory;

    public MarketStateDTO(MarketState market){
        this.date = DateConversion.toFormattedString(market.getDateTime());
        this.trajectory = market.getSentiment().toString();
        this.isRunning = market.isRunning();
        this.currentIntervalMs = market.getCurrentIntervalMs();
    }
}
