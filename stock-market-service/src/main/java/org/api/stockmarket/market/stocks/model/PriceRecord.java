package org.api.stockmarket.market.stocks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

/*
Saves daily stock history for one year.
 */
@Getter
@Setter
@NoArgsConstructor
public class PriceRecord implements Serializable {
    private String ticker;
    private Stock stock;
    private ZonedDateTime date;
    private Double stockPrice;

    public PriceRecord(Stock stock, ZonedDateTime date){
        this.stock = stock;
        this.ticker = stock.getTicker();
        this.date = date;
        this.stockPrice = stock.getPricingModel().getPrice().doubleValue();
    }
}
