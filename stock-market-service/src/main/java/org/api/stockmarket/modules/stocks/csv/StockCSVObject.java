package org.api.stockmarket.modules.stocks.csv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.api.stockmarket.common.csv.CSVObject;
import org.api.stockmarket.common.exceptions.ConfigurationException;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;

@Getter
@AllArgsConstructor
public class StockCSVObject implements CSVObject<Stock> {
    private String ticker;
    private String companyName;
    private String marketCap;
    private String sector;
    private String volatility;
    private String investorRating;
    private String initialPrice;

    public StockCSVObject(String[] line){
        if(line.length < 6){
            ConfigurationException.exit("stocks.csv has invalid data", "StockCSVReader");
        }
        ticker = line[0];
        companyName = line[0];
        marketCap = line[2];
        sector = line[3];
        volatility = line[4];
        investorRating = line[5];
        initialPrice = "0.0";
    }

    @Override
    public Stock mapToEntity() {
        if(!fieldsValid()) return null;

        MarketCap marketCap = MarketCap.map(this.marketCap);
        Volatility volatility = Volatility.map(this.volatility);
        InvestorRating investorRating = InvestorRating.map(this.investorRating);

        return switch (marketCap){
            case Large, Mega -> Stock.largeCap(ticker, companyName, sector, volatility, investorRating);
            case Mid -> Stock.midCap(ticker, companyName, sector, volatility, investorRating);
            case Small -> Stock.smallCap(ticker, companyName, sector, volatility, investorRating);
        };
    }

    @Override
    public boolean fieldsValid(){
        return ticker != null &&
                !ticker.contains("#") &&
                companyName != null &&
                marketCap != null &&
                sector != null &&
                volatility != null &&
                investorRating != null &&
                MarketCap.map(marketCap) != null &&
                Volatility.map(volatility) != null &&
                InvestorRating.map(investorRating) != null;
    }
}
