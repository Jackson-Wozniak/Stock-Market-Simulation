package stocktradingsimulator.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.indexfund.enums.FundTracking;
import stocktradingsimulator.indexfund.model.IndexFund;

@Getter
@Setter
public class StableIndexFund extends IndexFund {

    private boolean volatility;

    public StableIndexFund(double price){
        super("Stable Index Fund", price, FundTracking.VOLATILITY);
        this.volatility = false;
    }
}
