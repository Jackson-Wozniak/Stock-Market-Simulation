package org.api.stockmarket.modules.news.model;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.stocks.entity.Stock;

@Getter
@Setter
public class NewsStory {

    private String story;
    private double percentChange;

    private NewsStory(String story, double percentChange){
        this.story = story;
        this.percentChange = percentChange;
    }

    /*
    these positive/negative methods are here so that in the future design changes are easier
     */
    public static NewsStory positive(String story, double percentChange){
        return new NewsStory(story, percentChange);
    }

    public static NewsStory negative(String story, double percentChange){
        return new NewsStory(story, percentChange);
    }

    public String getStory(Stock stock){
        return story.replace("{name}", stock.getCompany().getCompanyName())
                .replace("{ticker}", stock.getTicker())
                .replace("{sector}", stock.getCompany().getSector());
    }
}
