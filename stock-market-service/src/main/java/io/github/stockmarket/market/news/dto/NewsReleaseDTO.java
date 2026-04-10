package io.github.stockmarket.market.news.dto;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.news.entity.NewsRelease;

import java.time.ZonedDateTime;

@Getter
@Setter
public class NewsReleaseDTO {
    private String ticker;
    private String eventType;
    private String template;
    private ZonedDateTime dateReleased;

    public NewsReleaseDTO(NewsRelease release){
        ticker = release.getStocks().get(0).getTicker();
        eventType = release.getNewsTemplate().getEventType();
        template = release.getNewsTemplate().getTemplate();
        dateReleased = release.getDateReleased();
    }
}
