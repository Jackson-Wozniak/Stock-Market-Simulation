package org.api.stockmarket.stocks.stock.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.news.entity.News;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.model.entity.Stock;
import org.api.stockmarket.stocks.stock.model.entity.StockHistory;
import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StockDto {

    private String ticker;
    private String companyName;
    private String sector;
    private MarketCap marketCap;
    private Double price;
    private Double lastDayPrice;
    private Double percentChange;
    private Integer momentum;
    private Integer momentumStreakInDays;
    private Volatility volatileStock;
    private InvestorRating investorRating;
    private List<News> newsHistory;
    private List<EarningsReport> earningsHistory;
    private List<StockHistory> stockHistory;

    public StockDto(Stock stock, List<StockHistory> stockHistory) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.sector = stock.getSector();
        this.marketCap = stock.getMarketCap();
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.momentum = stock.getMomentum();
        this.momentumStreakInDays = stock.getMomentumStreakInDays();
        this.volatileStock = stock.getVolatileStock();
        this.investorRating = stock.getInvestorRating();
        this.newsHistory = stock.getNewsHistory();
        this.earningsHistory = stock.getEarningsHistory();
        this.stockHistory = stockHistory;
        this.percentChange = getPercentChange(this.getPrice(), this.getLastDayPrice());
    }

    public double getPercentChange(double currentPrice, double lastDayPrice) {
        if(lastDayPrice == 0) return 0.0;
        return CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(
                (currentPrice - lastDayPrice) / lastDayPrice * 100);
    }
}
