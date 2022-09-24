package stocktradingsimulator.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import stocktradingsimulator.indexfund.enums.FundTracking;
import stocktradingsimulator.indexfund.model.IndexFund;
import stocktradingsimulator.indexfund.utils.Capitalize;

@Getter
@Setter
public class MarketCapIndexFund extends IndexFund {

    private String marketCap;

    public MarketCapIndexFund(String marketCap, double price){
        super(Capitalize.capitalize(marketCap) + " Cap Index Fund",
                price,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }
}
