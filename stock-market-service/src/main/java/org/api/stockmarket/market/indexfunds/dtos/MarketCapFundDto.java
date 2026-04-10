package org.api.stockmarket.market.indexfunds.dtos;

import lombok.Getter;
import org.api.stockmarket.market.indexfunds.model.subclass.MarketCapIndexFund;

@Getter
public class MarketCapFundDto extends IndexFundDto{
    private final String marketCap;

    public MarketCapFundDto(MarketCapIndexFund fund){
        super(fund);
        this.marketCap = fund.getMarketCap().toString();
    }
}
