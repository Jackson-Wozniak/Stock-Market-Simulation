package org.api.stockmarket.indexfund.defaults;

import org.api.stockmarket.indexfund.model.subclass.*;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import java.util.List;

public class DefaultIndexFunds {

    public static final List<MarketCapIndexFund> marketCapIndexFunds = List.of(
            new MarketCapIndexFund(MarketCap.Small, 5.0),
            new MarketCapIndexFund(MarketCap.Mid, 20.0),
            new MarketCapIndexFund(MarketCap.Large, 100.0)
    );

    public static final List<SectorIndexFund> sectorIndexFunds = List.of(
            new SectorIndexFund("Technology", 0.0)
    );

    public static final TotalMarketIndexFund totalMarketIndexFund = new TotalMarketIndexFund(0.0);
    public static final VolatilityIndexFund volatilityIndexFund = new VolatilityIndexFund(0.0);
    public static final StableIndexFund stableIndexFund = new StableIndexFund(0.0);

    public static int getAmountOfIndexFunds(){
        return marketCapIndexFunds.size() + sectorIndexFunds.size() + 3;
    }

}
