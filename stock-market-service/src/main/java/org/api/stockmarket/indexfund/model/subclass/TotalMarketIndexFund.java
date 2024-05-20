package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;

import jakarta.persistence.*;

@Entity
@Table(name = "total_market_index_fund")
@NoArgsConstructor
@Getter
@Setter
public class TotalMarketIndexFund extends IndexFund {

    public TotalMarketIndexFund(double price) {
        super("Total Market ETF", price, FundTracking.TOTAL_MARKET);
    }
}
