package org.api.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.stocks.model.Stock;

@Getter
@Setter
public class StockDetailedDTO extends StockBasicDTO{
    private String sector;
    private String marketCap;
    private String volatility;
    private String investorRating;

    public StockDetailedDTO(Stock stock) {
        super(stock);
        this.sector = stock.getCompany().getSector();
        this.marketCap = String.valueOf(stock.getCompany().getMarketCap());
        this.volatility = stock.getPricingModel().getAttributes().getVolatility().getName();
        this.investorRating = stock.getCompany().getInvestorRating().getName();
    }
}
