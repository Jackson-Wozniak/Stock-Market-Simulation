package io.github.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.stocks.model.Stock;

@Getter
@Setter
public class StockDetailedDTO extends StockBasicDTO{
    private String sector;
    private String marketCap;
    private String volatility;
    private String investorRating;

    public StockDetailedDTO(Stock stock) {
        super(stock);
        this.sector = stock.getCompanyAttributes().getSector();
        this.marketCap = String.valueOf(stock.getCompanyAttributes().getMarketCap());
        this.volatility = stock.getPricingModel().getAttributes().getVolatility().getName();
        this.investorRating = stock.getCompanyAttributes().getInvestorRating().getName();
    }
}
