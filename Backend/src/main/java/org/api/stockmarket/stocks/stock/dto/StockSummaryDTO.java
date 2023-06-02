package org.api.stockmarket.stocks.stock.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.earnings.dto.EarningsDTO;
import org.api.stockmarket.stocks.news.dto.NewsDTO;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.entity.StockHistory;
import org.api.tradinggame.account.utils.CalculateCostBasisAndProfits;

import java.util.List;
import java.util.stream.Collectors;

//This class is used when getting a list of stocks, where fields such as earnings and price history is not needed
@Getter
@Setter
public class StockSummaryDTO {

    private String ticker;
    private String companyName;
    private double price;
    private double lastDayPrice;
    private double percentChange;

    public StockSummaryDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyName();
        this.price = stock.getPrice();
        this.lastDayPrice = stock.getLastDayPrice();
        this.percentChange = getPercentChange();
    }

    public double getPercentChange() {
        if(this.lastDayPrice == 0) return 0.0;
        return CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(
                (this.price - this.lastDayPrice) / this.lastDayPrice * 100);
    }
}
