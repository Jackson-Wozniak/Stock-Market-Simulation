package org.api.stockmarket.market.utils;

import org.junit.jupiter.api.Test;
import org.api.stockmarket.market.utils.GetRandomNumber;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import static org.junit.jupiter.api.Assertions.*;

class GetRandomNumberTest {

    @Test
    void randomNumberShouldBeWithinBounds() {
        double randomLargeCapNumber = GetRandomNumber.getRandomNumberForStocks(MarketCap.Large);
        double randomMidCapNumber = GetRandomNumber.getRandomNumberForStocks(MarketCap.Mid);
        double randomSmallCapNumber = GetRandomNumber.getRandomNumberForStocks(MarketCap.Small);

        assert (randomLargeCapNumber > -.002 && randomLargeCapNumber < .002);
        assert (randomMidCapNumber > -.001 && randomMidCapNumber < .001);
        assert (randomSmallCapNumber > -.003 && randomSmallCapNumber < .003);
        assertThrows(IllegalArgumentException.class,
                () -> GetRandomNumber.getRandomNumberForStocks(MarketCap.valueOf("")));
    }

    @Test
    void randomPositiveNumbersShouldBeWithinBounds() {
        double randomLargeCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks(MarketCap.Large);
        double randomMidCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks(MarketCap.Mid);
        double randomSmallCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks(MarketCap.Small);

        assert (randomLargeCapNumber > 0 && randomLargeCapNumber < .002);
        assert (randomMidCapNumber > 0 && randomMidCapNumber < .001);
        assert (randomSmallCapNumber > 0 && randomSmallCapNumber < .003);
    }

}