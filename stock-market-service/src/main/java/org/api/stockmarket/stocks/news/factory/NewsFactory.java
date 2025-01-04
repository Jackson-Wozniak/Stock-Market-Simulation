package org.api.stockmarket.stocks.news.factory;

import org.api.stockmarket.stocks.news.properties.NewsPrompts;
import org.api.stockmarket.stocks.news.model.NewsStory;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NewsFactory {

    public NewsStory generate(boolean isPositiveNews){
        if(isPositiveNews) return NewsPrompts.positives.get(NewsPrompts.positives.size() - 1);
        return NewsPrompts.negatives.get(NewsPrompts.negatives.size() - 1);
    }
}
