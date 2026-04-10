package io.github.stockmarket.market.indexfunds.dtos;

import lombok.Getter;
import io.github.stockmarket.market.indexfunds.model.subclass.MarketCapIndexFund;

@Getter
public class MarketCapFundDto extends IndexFundDto{
    private final String marketCap;

    public MarketCapFundDto(MarketCapIndexFund fund){
        super(fund);
        this.marketCap = fund.getMarketCap().toString();
    }
}
