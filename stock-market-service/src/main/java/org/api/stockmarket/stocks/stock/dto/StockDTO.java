package org.api.stockmarket.stocks.stock.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.api.stockmarket.stocks.earnings.dto.EarningsDTO;
import org.api.stockmarket.stocks.news.dto.NewsDTO;
import org.api.stockmarket.stocks.stock.entity.Stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StockDTO {

    private String ticker;
    private String companyName;
    private String sector;
    private String marketCap;
    private double price;
    private double lastDayPrice;
    private double percentChange;
    private int momentum;
    private int momentumStreakInDays;
    private String volatileStock;
    private String investorRating;

    public StockDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = String.valueOf(stock.getMarketCap());
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.momentum = stock.getMomentum();
        this.momentumStreakInDays = stock.getMomentumStreakInDays();
        this.volatileStock = String.valueOf(stock.getVolatileStock());
        this.investorRating = String.valueOf(stock.getInvestorRating());
        this.percentChange = getPercentChange(this.getPrice(), this.getLastDayPrice());
    }

    public double getPercentChange(double currentPrice, double lastDayPrice) {
        if(lastDayPrice == 0) return 0.0;
        //TODO: add rounding to 2 decimal places
        return (currentPrice - lastDayPrice) / lastDayPrice * 100;
    }
}
