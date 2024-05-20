package org.api.stockmarket.stocks.stock.entity.idclass;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class StockPriceHistoryId implements Serializable {

    
    @Column(name = "market_date")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime  marketDate;

    @Column(name = "ticker")
    private String ticker;
}
