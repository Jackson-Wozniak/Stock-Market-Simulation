package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.utils.Capitalize;

@Getter
@Setter
public class MarketCapIndexFund extends IndexFund {

    private String marketCap;

    public MarketCapIndexFund(String marketCap, double price){
        super(Capitalize.capitalize(marketCap) + " Cap Index Fund",
                price,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }
}
