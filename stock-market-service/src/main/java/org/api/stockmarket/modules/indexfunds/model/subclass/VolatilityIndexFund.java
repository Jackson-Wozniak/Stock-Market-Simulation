package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.dtos.IndexFundDto;
import org.api.stockmarket.indexfund.dtos.VolatileIndexFundDto;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.stocks.stock.enums.Volatility;

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
