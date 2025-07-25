package org.api.stockmarket.engine.dtos;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.engine.entity.Market;
import org.api.stockmarket.common.utils.DateConversion;

@Getter
@Setter
public class MarketDTO {

    private String date;
    private String trajectory;
    private double lastMonthPrice;

    public MarketDTO(Market market){
        this.date = DateConversion.toFormattedString(market.getDate());
        this.trajectory = market.getMarketTrajectory().toString();
        this.lastMonthPrice = market.getLastMonthAveragePrice();
    }
}
