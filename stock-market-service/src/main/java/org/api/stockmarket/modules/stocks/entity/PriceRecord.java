package org.api.stockmarket.modules.stocks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.api.stockmarket.core.entity.BaseEntity;

import java.io.Serializable;
import java.time.ZonedDateTime;

/*
Saves daily stock history for one year.
 */
@Entity(name = "price_history")
@Table(name = "stock_history")
@Getter
@Setter
@NoArgsConstructor
public class PriceRecord extends BaseEntity implements Serializable {
    @Column(name = "ticker")
    private String ticker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "price")
    private Double stockPrice;

    public PriceRecord(Stock stock, ZonedDateTime date){
        this.stock = stock;
        this.ticker = stock.getTicker();
        this.date = date;
        this.stockPrice = stock.getPricingModel().getPrice().doubleValue();
    }
}
