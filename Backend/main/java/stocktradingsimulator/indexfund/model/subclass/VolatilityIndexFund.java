package stocktradingsimulator.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.indexfund.enums.FundTracking;
import stocktradingsimulator.indexfund.model.IndexFund;

@Getter
@Setter
public class VolatilityIndexFund extends IndexFund {

    private boolean volatility;

    public VolatilityIndexFund(double price){
        super("Volatile Index Fund", price, FundTracking.VOLATILITY);
        this.volatility = true;
    }
}
