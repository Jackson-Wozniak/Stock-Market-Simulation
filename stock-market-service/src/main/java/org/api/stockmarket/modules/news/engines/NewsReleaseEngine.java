package org.api.stockmarket.modules.news.engines;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.modules.news.entity.NewsRelease;
import org.api.stockmarket.modules.news.service.NewsReleaseService;
import org.api.stockmarket.modules.news.service.NewsTemplateService;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestmentStyle;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
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

    public List<NewsRelease> executeNewsCycle(ZonedDateTime date){
        List<Stock> stocks = stockService.getAllStocks();

        List<NewsRelease> releases = stocks.stream()
                .map(stock -> generateNewsReleaseOrNull(stock, date))
                .filter(Objects::nonNull)
                .toList();
        newsReleaseService.saveNewsReleases(releases);

        return List.of();
    }

    /*
    This method uses the stock's company attributes to determine the likelihood
    of positive or negative stories. This design ensures that the default attributes
    of a stock guide the likelihood of news sentiment over time
     */
    public NewsRelease generateNewsReleaseOrNull(Stock stock, ZonedDateTime date){
        /*
        TODO:
            - calculate a % chance of both positive and negative story
            - get random number
            - if within range of either one, create a release
            - if not within range, return null
         */
        InvestorRating rating = stock.getCompany().getInvestorRating();
        InvestmentStyle style = stock.getCompany().getInvestmentStyle();
        return null;
    }

    public double percentChanceOfPositiveNews(InvestorRating rating, InvestmentStyle style){
        double averagePositivityScore = (rating.getPositivityScoreOutOf100()
                + style.getPositivityScoreOutOf100()) / 2.0;
        if(averagePositivityScore < 50.0)
            return MarketEnvironmentProperties.MIN_PERCENT_CHANCE_POSITIVE_NEWS;
        return MarketEnvironmentProperties.MAX_PERCENT_CHANCE_POSITIVE_NEWS
                * (averagePositivityScore / 100.0);
    }

    public double percentChanceOfNegativeNews(InvestorRating rating, InvestmentStyle style){
        double averagePositivityScore = (rating.getPositivityScoreOutOf100()
                + style.getPositivityScoreOutOf100()) / 2.0;
        if(averagePositivityScore > 50.0)
            return MarketEnvironmentProperties.MIN_PERCENT_CHANCE_NEGATIVE_NEWS;
        return MarketEnvironmentProperties.MAX_PERCENT_CHANCE_NEGATIVE_NEWS
                * ((100.0 - averagePositivityScore) / 100.0);
    }
}
