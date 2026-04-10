package io.github.stockmarket.market.stocks.csv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import io.github.stockmarket.core.csv.CSVObject;
import io.github.stockmarket.core.exception.ConfigurationException;
import io.github.stockmarket.market.stocks.model.Stock;
import io.github.stockmarket.market.stocks.enums.InvestmentStyle;
import io.github.stockmarket.market.stocks.enums.InvestorRating;
import io.github.stockmarket.market.stocks.enums.MarketCap;
import io.github.stockmarket.market.stocks.enums.PriceVolatility;

@Getter
@AllArgsConstructor
public class StockCSVObject implements CSVObject<Stock> {
    private String ticker;
    private String companyName;
    private MarketCap marketCap;
    private String sector;
    private PriceVolatility volatility;
    private InvestorRating investorRating;
    private InvestmentStyle investorStyle;
    private double initialPrice;

    public StockCSVObject(String[] line){
        if(line.length < 7){
            ConfigurationException.failAndExit("stocks.csv has invalid data", "StockCSVReader");
        }
        try{
            ticker = line[0];
            companyName = line[1];
            marketCap = MarketCap.fromValue(Integer.parseInt(line[2]));
            sector = line[3];
            volatility = PriceVolatility.fromName(line[4]);
            investorRating = InvestorRating.fromValue(Integer.parseInt(line[5]));
            investorStyle = InvestmentStyle.fromValue(Integer.parseInt(line[6]));
            initialPrice = Double.parseDouble(line[7]);
        }catch (Exception ex){
            ConfigurationException.failAndExit("stocks.csv has invalid data", "StockCSVReader");
        }
    }

    @Override
    public boolean fieldsValid(){
        return true;
    }
}
