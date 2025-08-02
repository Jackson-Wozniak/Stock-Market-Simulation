package org.api.stockmarket.modules.stocks.factory;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.stocks.csv.StockCSVObject;
import org.api.stockmarket.modules.stocks.entity.Company;
import org.api.stockmarket.modules.stocks.entity.PricingModel;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.stereotype.Component;

import static org.api.stockmarket.modules.stocks.utils.StockFactoryUtils.*;

@Component
@AllArgsConstructor
public class StockFactory {

    public Stock create(StockCSVObject csvData){
        Stock stock = new Stock(csvData.getTicker());
        Company company = new Company.Builder(stock)
                        .companyDetails(csvData.getCompanyName(),csvData.getSector(), csvData.getMarketCap())
                        .investmentProfile(csvData.getInvestorRating(), csvData.getInvestorStyle())
                        .build();
        stock.setCompany(company);
        stock.setPricingModel(new PricingModel.Builder(stock)
                .details(csvData.getInitialPrice(), csvData.getVolatility())
                .innovation(defaultInnovationFactor(company),2,0)
                .investorConfidence(defaultInvestorConfidence(company),.2,0)
                .liquidity(defaultLiquidity(company),.2,0)
                .newsSentiment(0,.2,0)
                .tradingDemand(defaultTradingDemand(company),.2,0)
                .build());
        return stock;
    }
}
