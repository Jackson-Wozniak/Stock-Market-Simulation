package org.api.stockmarket.stocks.stock.entity.idclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class StockHistoryId implements Serializable {

    @Column(name = "market_date")
    private String marketDate;

    @Column(name = "stock_ticker")
    private String ticker;
}
