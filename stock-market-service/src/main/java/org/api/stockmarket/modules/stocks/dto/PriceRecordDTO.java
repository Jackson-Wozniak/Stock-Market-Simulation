package org.api.stockmarket.modules.stocks.dto;

import java.time.ZonedDateTime;

import org.api.stockmarket.modules.stocks.entity.PriceRecord;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceRecordDTO {
    private ZonedDateTime  marketDate;
    private double stockPrice;

    public PriceRecordDTO(PriceRecord history){
        this.marketDate = history.getDate();
        this.stockPrice = history.getStockPrice();
    }
}
