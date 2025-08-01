package org.api.stockmarket.modules.stocks.factory;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.csv.StockCSVObject;
import org.api.stockmarket.modules.stocks.entity.Company;
import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StockFactory {

    public Stock create(StockCSVObject csvData){
        Stock stock = new Stock();
        stock = new Stock(csvData.getTicker());
        stock.setCompany(new Company.Builder(stock)
                        .companyDetails(csvData.getCompanyName(),csvData.getSector(), csvData.getMarketCap())
                        .investmentProfile(csvData.getInvestorRating(), csvData.getInvestorStyle())
                        .build());
        stock.setPricingModel(new PricingModel.Builder(stock)
                .details(csvData.getInitialPrice(), csvData.getVolatility())
                .innovation(0,0,0)
                .investorConfidence(0,0,0)
                .liquidity(0,0,0)
                .newsSentiment(0,0,0)
                .tradingDemand(0,0,0)
                .build());
        return stock;
    }
}
