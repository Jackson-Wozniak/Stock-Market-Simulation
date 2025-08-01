package org.api.stockmarket.modules.stocks.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
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
public class PriceRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticker")
    private Stock stock;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "price")
    private Double stockPrice;

    public PriceRecord(Stock stock, ZonedDateTime date, double price){
        this.stock = stock;
        this.date = date;
        this.stockPrice = price;
    }
}
