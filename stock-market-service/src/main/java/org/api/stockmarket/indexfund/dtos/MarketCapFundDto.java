package org.api.stockmarket.indexfund.dtos;

import lombok.Getter;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;

@Getter
public class MarketCapFundDto {
    private final String name;
    private final String marketCap;
    private final double price;

    public MarketCapFundDto(MarketCapIndexFund fund){
        this.name = fund.getName();
        this.marketCap = fund.getMarketCap().toString();
        this.price = fund.getPrice();
    }
}
