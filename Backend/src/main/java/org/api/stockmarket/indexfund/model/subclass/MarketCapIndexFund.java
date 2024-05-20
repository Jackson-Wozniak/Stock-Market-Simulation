package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import jakarta.persistence.*;

@Entity
@Table(name = "market_cap_index_fund")
@NoArgsConstructor
@Getter
@Setter
public class MarketCapIndexFund extends IndexFund {

    @Column(name = "market_cap")
    private MarketCap marketCap;

    public MarketCapIndexFund(MarketCap marketCap, double price) {
        super(marketCap + " Cap Index Fund",
                price,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }

    public MarketCapIndexFund(MarketCap marketCap) {
        super(marketCap + " Cap Index Fund",
                0.0,
                FundTracking.MARKET_CAP);
        this.marketCap = marketCap;
    }
}
