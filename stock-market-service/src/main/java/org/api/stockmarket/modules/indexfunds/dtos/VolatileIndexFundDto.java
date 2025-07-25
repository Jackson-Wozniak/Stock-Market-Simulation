package org.api.stockmarket.indexfund.dtos;

import lombok.Getter;
import org.api.stockmarket.indexfund.model.subclass.VolatilityIndexFund;

@Getter
public class VolatileIndexFundDto extends IndexFundDto{

    private final String volatility;

    public VolatileIndexFundDto(VolatilityIndexFund fund){
        super(fund);
        this.volatility = fund.getVolatility().toString();
    }
}
