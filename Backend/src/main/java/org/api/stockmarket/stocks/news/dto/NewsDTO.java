package org.api.stockmarket.stocks.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.news.entity.News;

@Getter
@Setter
public class NewsDTO {

    private String event;
    private String dateReleased;

    public NewsDTO(News news){
        this.event = news.getEvent();
        this.dateReleased = news.getDateReleased();
    }
}
