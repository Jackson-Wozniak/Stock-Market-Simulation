package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;

@Getter
@Setter
public class VolatilityIndexFund extends IndexFund {

    private Boolean volatility;

    public VolatilityIndexFund(double price) {
        super("Volatile Index Fund", price, FundTracking.VOLATILITY);
        this.volatility = true;
    }
}
