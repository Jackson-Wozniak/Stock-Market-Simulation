package org.api.stockmarket.stocks.stock.model.entity.idclass;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
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
