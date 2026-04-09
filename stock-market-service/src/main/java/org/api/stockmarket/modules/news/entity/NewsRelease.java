package org.api.stockmarket.modules.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.entity.Stock;

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