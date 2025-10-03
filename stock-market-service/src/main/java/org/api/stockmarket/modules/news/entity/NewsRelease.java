package org.api.stockmarket.modules.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.entity.Stock;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "newsRelease")
@Table(name = "news_releases")
@NoArgsConstructor
@Getter
@Setter
public class NewsRelease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Stock> stocks = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "template")
    private NewsTemplate newsTemplate;

    @Column(name = "date_released")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime dateReleased;

    public NewsRelease(Stock stock, NewsTemplate newsTemplate, ZonedDateTime dateReleased) {
        this.stocks.add(stock);
        this.newsTemplate = newsTemplate;
        this.dateReleased = dateReleased;
    }
}