package org.api.stockmarket.stocks.stock.model.object;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.stocks.stock.defaults.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.model.entity.Stock;

import javax.persistence.Entity;

/*
    Entity To save Default Stocks to database
    Sets prices and momentum to default values
 */
//@Entity
//@Getter
@Setter
@NoArgsConstructor
public class DefaultStock {

    private String ticker;
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private Double price;
    private Double lastDayPrice;
    private Integer momentum;
    private Integer momentumStreakInDays;
    private Volatility volatileStock;
    private Integer investorRating;

    public DefaultStock(String ticker,
                        String companyName,
                        String sector,
                        MarketCap marketCap,
                        Volatility volatileStock,
                        Integer investorRating) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;
        this.volatileStock = volatileStock;
        this.investorRating = investorRating;
        this.price = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.lastDayPrice = DefaultStockPrices.getDefaultPriceWithCap(marketCap);
        this.momentum = 0;
        this.momentumStreakInDays = 0;
    }

    public void updatePriceWithFormula(){
    }
}
