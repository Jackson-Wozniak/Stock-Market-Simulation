package org.api.stockmarket.stocks.stock.model.entity.idclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.api.stockmarket.stocks.stock.model.entity.Stock;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class StockHistoryId implements Serializable{

    private String marketDate;

    private String stockTicker;
}
