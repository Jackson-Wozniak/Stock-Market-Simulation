package org.api.stockmarket.modules.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.modules.indexfunds.enums.FundTracking;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;

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
