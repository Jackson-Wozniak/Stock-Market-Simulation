package org.api.stockmarket.indexfund.dtos;

import lombok.Getter;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;

@Getter
public class MarketCapFundDto extends IndexFundDto{
    private final String marketCap;

    public MarketCapFundDto(MarketCapIndexFund fund){
        super(fund);
        this.marketCap = fund.getMarketCap().toString();
    }
}
