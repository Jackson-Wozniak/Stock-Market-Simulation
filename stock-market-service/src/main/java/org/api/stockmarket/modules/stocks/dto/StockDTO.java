package org.api.stockmarket.modules.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.entity.Stock;

//This class is used when getting a list of stocks, where fields such as earnings and price history is not needed
@Getter
@Setter
public class StockDTO {

    private String ticker;
    private String companyName;
    private double price;
    private double lastDayPrice;
    private double percentChange;

    public StockDTO(Stock stock) {
        //TODO
//        this.ticker = stock.getTicker();
//        this.companyName = stock.getCompanyName();
//        this.price = stock.getPrice();
//        this.lastDayPrice = stock.getLastDayPrice();
//        this.percentChange = getPercentChange();
    }

    public double getPercentChange() {
        if(this.lastDayPrice == 0) return 0.0;
        //TODO: add 2 decimal place rounding
        return (this.price - this.lastDayPrice) / this.lastDayPrice * 100;
    }
}
