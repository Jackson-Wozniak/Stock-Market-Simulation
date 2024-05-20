package org.api.stockmarket.stocks.news.dto;

import java.time.ZonedDateTime;

import org.api.stockmarket.stocks.news.entity.News;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDTO {

    private String event;
    private ZonedDateTime dateReleased;

    public NewsDTO(News news){
        this.event = news.getEvent();
        this.dateReleased = news.getDateReleased();
    }
}
