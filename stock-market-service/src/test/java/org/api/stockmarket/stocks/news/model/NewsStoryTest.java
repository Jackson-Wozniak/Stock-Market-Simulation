package org.api.stockmarket.stocks.news.model;

import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.api.stockmarket.stocks.stock.properties.DefaultStockPrices.*;

class NewsStoryTest {

    private static final Stock SMALL_CAP = Stock.smallCap(
            "SMALL", "Small Name",
            "Small Sector", Volatility.NORMAL, InvestorRating.Neutral);
    private static final Stock MID_CAP = Stock.midCap(
            "MID", "Mid Name",
            "Mid Sector", Volatility.NORMAL, InvestorRating.Neutral);
    private static final Stock LARGE_CAP = Stock.largeCap(
            "LARGE", "Large Name",
            "Large Sector", Volatility.NORMAL, InvestorRating.Neutral);

    @Test
    void valuesAreReplacedInStory(){
        NewsStory story = NewsStory.positive("{ticker} {name} {sector}", 0);

        assertEquals("SMALL Small Name Small Sector", story.getStory(SMALL_CAP));
        assertEquals("MID Mid Name Mid Sector", story.getStory(MID_CAP));
        assertEquals("LARGE Large Name Large Sector", story.getStory(LARGE_CAP));
    }

    @Test
    void percentChanges(){
        NewsStory zeroChange = NewsStory.positive("", 1);
        NewsStory goToZero = NewsStory.positive("", 0);
        NewsStory doubleChange = NewsStory.positive("", 2);
        NewsStory onePointOne = NewsStory.positive("", 1.1);

        assertEquals(DEFAULT_SMALL_CAP_PRICE, (SMALL_CAP.multiplyPrice(zeroChange.getPercentChange())));
        assertEquals(DEFAULT_MID_CAP_PRICE, (MID_CAP.multiplyPrice(zeroChange.getPercentChange())));
        assertEquals(DEFAULT_LARGE_CAP_PRICE, (LARGE_CAP.multiplyPrice(zeroChange.getPercentChange())));

        assertEquals(0, (SMALL_CAP.multiplyPrice(goToZero.getPercentChange())));
        assertEquals(0, (MID_CAP.multiplyPrice(goToZero.getPercentChange())));
        assertEquals(0, (LARGE_CAP.multiplyPrice(goToZero.getPercentChange())));

        assertEquals(10.0, (SMALL_CAP.multiplyPrice(doubleChange.getPercentChange())));
        assertEquals(40.0, (MID_CAP.multiplyPrice(doubleChange.getPercentChange())));
        assertEquals(200.0, (LARGE_CAP.multiplyPrice(doubleChange.getPercentChange())));

        assertEquals(5.5, (SMALL_CAP.multiplyPrice(onePointOne.getPercentChange())));
        assertEquals(22.0, (MID_CAP.multiplyPrice(onePointOne.getPercentChange())));
        assertEquals(110.0, (LARGE_CAP.multiplyPrice(onePointOne.getPercentChange())));
    }
}