package org.api.stockmarket.modules.indexfunds.model;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.modules.indexfunds.enums.FundTracking;

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
