package org.api.stockmarket.market.payload;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.utils.DateConversion;

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
