package org.api.stockmarket.modules.news.factory;

import org.api.stockmarket.modules.news.model.NewsStory;
import org.api.stockmarket.modules.news.properties.NewsPrompts;
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
