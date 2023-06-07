package org.api.stockmarket.stocks.stock.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.StockPriceHistory;

@Getter
@Setter
public class StockPriceHistoryDTO {

    private String marketDate;
    private double stockPrice;

    public StockPriceHistoryDTO(StockPriceHistory history){
        this.marketDate = history.getId().getMarketDate();
        this.stockPrice = history.getStockPrice();
    }
}
