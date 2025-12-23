package org.api.stockmarket.modules.news.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.news.entity.NewsTemplate;
import org.api.stockmarket.modules.stocks.entity.Stock;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

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
