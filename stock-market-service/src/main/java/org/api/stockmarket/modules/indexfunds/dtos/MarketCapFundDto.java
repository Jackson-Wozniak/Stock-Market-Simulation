package org.api.stockmarket.modules.indexfunds.dtos;

import lombok.Getter;
import org.api.stockmarket.modules.indexfunds.model.subclass.MarketCapIndexFund;

@Getter
public class MarketCapFundDto extends IndexFundDto{
    private final String marketCap;

    public MarketCapFundDto(MarketCapIndexFund fund){
        super(fund);
        this.marketCap = fund.getMarketCap().toString();
    }
}
