package io.github.stockmarket.market.news.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.github.stockmarket.market.stocks.model.Stock;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class NewsRelease {
    private List<Stock> stocks = new ArrayList<>();
    private NewsTemplate newsTemplate;
    private ZonedDateTime dateReleased;

    public NewsRelease(Stock stock, NewsTemplate newsTemplate, ZonedDateTime dateReleased) {
        this.stocks.add(stock);
        this.newsTemplate = newsTemplate;
        this.dateReleased = dateReleased;
    }
}