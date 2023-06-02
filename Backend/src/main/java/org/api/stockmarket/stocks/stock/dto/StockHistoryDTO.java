package org.api.stockmarket.stocks.stock.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.StockHistory;

@Getter
@Setter
public class StockHistoryDTO {

    private String marketDate;
    private double stockPrice;

    public StockHistoryDTO(StockHistory history){
        this.marketDate = history.getMarketDate();
        this.stockPrice = history.getStockPrice();
    }
}
