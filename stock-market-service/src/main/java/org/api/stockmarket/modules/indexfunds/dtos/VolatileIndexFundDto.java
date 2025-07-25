package org.api.stockmarket.modules.indexfunds.dtos;

import lombok.Getter;
import org.api.stockmarket.modules.indexfunds.model.subclass.VolatilityIndexFund;

@Getter
public class VolatileIndexFundDto extends IndexFundDto{

    private final String volatility;

    public VolatileIndexFundDto(VolatilityIndexFund fund){
        super(fund);
        this.volatility = fund.getVolatility().toString();
    }
}
