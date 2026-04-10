package io.github.stockmarket.market.indexfunds.model;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.indexfunds.dtos.IndexFundDto;
import io.github.stockmarket.market.indexfunds.enums.FundTracking;

@Getter
@Setter
public abstract class IndexFund {

    private String name;
    private double price;
    private FundTracking fundTracking;

    public IndexFund(String name, double price, FundTracking fundTracking){
        this.name = name;
        this.price = (Math.round(price * 100.00) / 100.00);
        this.fundTracking = fundTracking;
    }

    public abstract IndexFundDto toDto();
}
