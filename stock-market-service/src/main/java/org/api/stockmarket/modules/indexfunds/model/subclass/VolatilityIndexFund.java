package org.api.stockmarket.modules.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.modules.indexfunds.dtos.VolatileIndexFundDto;
import org.api.stockmarket.modules.indexfunds.enums.FundTracking;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.stocks.enums.Volatility;

@Getter
@Setter
public class VolatilityIndexFund extends IndexFund {

    private Volatility volatility;

    public VolatilityIndexFund(Volatility volatility, double price) {
        super("Volatile Index Fund", price, FundTracking.VOLATILITY);
        this.volatility = volatility;
    }

    @Override
    public IndexFundDto toDto(){
        return new VolatileIndexFundDto(this);
    }
}
