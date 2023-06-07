package org.api.stockmarket.stocks.stock.entity.idclass;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceHistoryId implements Serializable {

    @Column(name = "market_date")
    private String marketDate;

    @Column(name = "ticker")
    private String ticker;
}
