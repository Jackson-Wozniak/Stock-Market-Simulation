package org.api.stockmarket.stocks.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.idclass.StockPriceHistoryId;

import jakarta.persistence.*;
import java.io.Serializable;

/*
Saves daily stock history for one year.
 */
@Entity
@Table(name = "stock_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockPriceHistory implements Serializable {

    @EmbeddedId
    private StockPriceHistoryId id;

    @MapsId(value = "ticker")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    @Column(name = "price")
    private Double stockPrice;
}
