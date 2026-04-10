package io.github.stockmarket.market.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.indexfunds.dtos.IndexFundDto;
import io.github.stockmarket.market.indexfunds.dtos.MarketCapFundDto;
import io.github.stockmarket.market.indexfunds.enums.FundTracking;
import io.github.stockmarket.market.indexfunds.model.IndexFund;
import io.github.stockmarket.market.stocks.enums.MarketCap;

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

    @Override
    public IndexFundDto toDto(){
        return new MarketCapFundDto(this);
    }
}
