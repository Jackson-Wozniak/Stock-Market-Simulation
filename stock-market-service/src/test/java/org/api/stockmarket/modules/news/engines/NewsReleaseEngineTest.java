package org.api.stockmarket.modules.news.engines;

import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.modules.stocks.entity.Company;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestmentStyle;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.junit.jupiter.api.Test;

import static org.api.stockmarket.engine.properties.MarketEnvironmentProperties.*;
import static org.junit.jupiter.api.Assertions.*;

class NewsReleaseEngineTest {
    @Test
    public void testPositiveNewsPercentChance(){
        NewsReleaseEngine engine = new NewsReleaseEngine(null, null, null);

        double expectedPercent = MAX_PERCENT_CHANCE_POSITIVE_NEWS * .85;
        assertEquals(expectedPercent, engine.percentChanceOfPositiveNews(
                InvestorRating.STRONG_BUY, InvestmentStyle.BLUE_CHIP));

        assertEquals(MIN_PERCENT_CHANCE_POSITIVE_NEWS, engine.percentChanceOfPositiveNews(
                InvestorRating.SELL, InvestmentStyle.MEME));
    }

    @Test
    void testNegativeNewsPercentChance(){
        NewsReleaseEngine engine = new NewsReleaseEngine(null, null, null);

        double expectedPercent = MAX_PERCENT_CHANCE_NEGATIVE_NEWS * .9;
        assertEquals(expectedPercent, engine.percentChanceOfNegativeNews(
                InvestorRating.SELL, InvestmentStyle.MEME));

        assertEquals(MIN_PERCENT_CHANCE_NEGATIVE_NEWS, engine.percentChanceOfNegativeNews(
                InvestorRating.STRONG_BUY, InvestmentStyle.BLUE_CHIP));
    }
}