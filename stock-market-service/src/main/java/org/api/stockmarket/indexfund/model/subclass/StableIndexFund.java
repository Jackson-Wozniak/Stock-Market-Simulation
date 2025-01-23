package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;

@Getter
@Setter
public class StableIndexFund extends IndexFund {

    private Boolean volatility;

    public StableIndexFund(double price) {
        super("Stable Index Fund", price, FundTracking.VOLATILITY);
        this.volatility = false;
    }
}
