package org.api.stockmarket.stocks.stock.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.model.entity.idclass.StockHistoryId;

import javax.persistence.*;
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
@IdClass(StockHistoryId.class)
public class StockHistory implements Serializable {

    @Id
    private String marketDate;

    @Id
    @JsonIgnore
    private String ticker;

    @Column(name = "price")
    private Double stockPrice;
}
