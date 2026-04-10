package io.github.stockmarket.market.stocks.factory;

import lombok.AllArgsConstructor;
import io.github.stockmarket.market.stocks.csv.StockCSVObject;
import io.github.stockmarket.market.stocks.model.Company;
import io.github.stockmarket.market.stocks.model.PricingModel;
import io.github.stockmarket.market.stocks.model.Stock;
import org.springframework.stereotype.Component;

import static io.github.stockmarket.market.stocks.utils.StockFactoryUtils.*;

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
