package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

@Getter
@Setter
public class MarketCapIndexFund extends IndexFund {

    private MarketCap marketCap;

    public MarketCapIndexFund(MarketCap marketCap, double price) {
        super(marketCap + " Cap Index Fund",
                price,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }

    public MarketCapIndexFund(MarketCap marketCap) {
        super(marketCap + " Cap Index Fund",
                0.0,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }
}
