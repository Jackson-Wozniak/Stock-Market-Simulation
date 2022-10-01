package org.api.stockmarket.stocks.stock.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.utils.PercentChange;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StockDto extends Stock implements Serializable {

    private String ticker;
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private Double price;
    private Double lastDayPrice;
    private Double percentChange;
    private Integer momentum;
    private Integer momentumStreakInDays;
    private Boolean volatileStock;
    private List<News> newsHistory;
    private List<EarningsReport> earningsHistory;

    public StockDto(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = stock.getMarketCap();
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.momentum = stock.getMomentum();
        this.momentumStreakInDays = stock.getMomentumStreakInDays();
        this.volatileStock = stock.getVolatileStock();
        this.newsHistory = stock.getNewsHistory();
        this.earningsHistory = stock.getEarningsHistory();
        this.percentChange = PercentChange.percentChange(this.getPrice(), this.getLastDayPrice());
    }
}
