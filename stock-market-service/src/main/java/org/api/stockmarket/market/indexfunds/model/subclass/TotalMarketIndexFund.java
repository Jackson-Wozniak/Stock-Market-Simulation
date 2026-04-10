package org.api.stockmarket.market.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.market.indexfunds.enums.FundTracking;
import org.api.stockmarket.market.indexfunds.model.IndexFund;

@Getter
@Setter
public class TotalMarketIndexFund extends IndexFund {

    public TotalMarketIndexFund(double price) {
        super("Total Market ETF", price, FundTracking.TOTAL_MARKET);
    }

    @Override
    public IndexFundDto toDto(){
        return new IndexFundDto(this);
    }
}
