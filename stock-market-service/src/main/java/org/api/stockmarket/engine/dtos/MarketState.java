package org.api.stockmarket.engine.dtos;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.common.utils.DateConversion;

@Getter
@Setter
public class MarketState {

    private String date;
    private String trajectory;

    public MarketState(org.api.stockmarket.engine.entity.MarketState market){
        this.date = DateConversion.toFormattedString(market.getDateTime());
        this.trajectory = market.getSentiment().toString();
    }
}
