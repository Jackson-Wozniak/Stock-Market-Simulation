package org.api.stockmarket.modules.stocks.dto;

import org.api.stockmarket.modules.stocks.entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DetailedStockDTO {
    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private double price;
    private String volatility;
    private String investorRating;

    public DetailedStockDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompany().getCompanyName();
        this.sector = stock.getCompany().getSector();
        this.marketCap = String.valueOf(stock.getCompany().getMarketCap());
        this.price = stock.getPrice();
        this.volatility = stock.getPricingModel().getAttributes().getVolatility().getName();
        this.investorRating = stock.getCompany().getInvestorRating().getName();
    }
}
