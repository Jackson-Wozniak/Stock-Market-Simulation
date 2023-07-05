package org.api.stockmarket.stocks.stock.dto;

import java.time.ZonedDateTime;

import org.api.stockmarket.stocks.stock.entity.StockPriceHistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceHistoryDTO {

    
    private ZonedDateTime  marketDate;
    private double stockPrice;

    public StockPriceHistoryDTO(StockPriceHistory history){
        this.marketDate = history.getId().getMarketDate();
        this.stockPrice = history.getStockPrice();
    }
}
