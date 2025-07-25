package org.api.stockmarket.stocks.news.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.Stock;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

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
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dateReleased;

    public News(Stock stock, String event, ZonedDateTime dateReleased) {
        this.stock = stock;
        this.event = event;
        this.dateReleased = dateReleased;
    }
}