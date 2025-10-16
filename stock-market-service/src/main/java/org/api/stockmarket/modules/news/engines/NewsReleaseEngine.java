package org.api.stockmarket.modules.news.engines;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.news.service.NewsReleaseService;
import org.api.stockmarket.modules.news.service.NewsTemplateService;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/*
Class that manages the creation and release of news stories
Begin by releasing news stories at a certain % chance, and then generate/save new stories
 */
@Component
@AllArgsConstructor
public class NewsReleaseEngine {
    private final StockService stockService;
    private final NewsTemplateService newsTemplateService;
    private final NewsReleaseService newsReleaseService;
    private static final Random random = new Random();

    public List<NewsRelease> executeNewsCycle(ZonedDateTime date){
        List<Stock> stocks = stockService.getAllStocks();

        List<NewsRelease> releases = stocks.stream()
                .map(stock -> generateNewsReleaseOrNull(stock, date))
                .filter(Objects::nonNull)
                .toList();
        newsReleaseService.saveNewsReleases(releases);

        return List.of();
    }

    public NewsRelease generateNewsReleaseOrNull(Stock stock, ZonedDateTime date){
        /*
        TODO:
            - calculate a % chance of both positive and negative story
            - get random number
            - if within range of either one, create a release
            - if not within range, return null
         */
        return null;
    }
}
