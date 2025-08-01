package org.api.stockmarket.modules.stocks.dto;

import java.time.ZonedDateTime;

import org.api.stockmarket.modules.stocks.entity.PriceRecord;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockPriceHistoryDTO {

    
    private ZonedDateTime  marketDate;
    private double stockPrice;

    public StockPriceHistoryDTO(PriceRecord history){
        this.marketDate = history.getId().getMarketDate();
        this.stockPrice = history.getStockPrice();
    }
}
