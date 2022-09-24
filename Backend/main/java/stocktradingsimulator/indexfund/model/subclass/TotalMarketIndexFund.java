package stocktradingsimulator.indexfund.model.subclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stocktradingsimulator.indexfund.enums.FundTracking;
import stocktradingsimulator.indexfund.model.IndexFund;

@Getter
@Setter
public class TotalMarketIndexFund extends IndexFund {

    public TotalMarketIndexFund(double price){
        super("Total Market ETF", price, FundTracking.TOTAL_MARKET);
    }
}
