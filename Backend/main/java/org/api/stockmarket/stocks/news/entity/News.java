package org.api.stockmarket.stocks.news.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.model.entity.Stock;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonBackReference
    private Stock stock;

    @Column(name = "news_event")
    private String event;

    @Column(name = "date_released")
    private String dateReleased;

    public News(Stock stock, String event, String dateReleased){
        this.stock = stock;
        this.event = event;
        this.dateReleased = dateReleased;
    }
}