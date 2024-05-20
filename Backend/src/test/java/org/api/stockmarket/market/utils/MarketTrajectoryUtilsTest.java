package org.api.stockmarket.market.utils;

import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.junit.jupiter.api.Test;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.enums.MarketTrajectory;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.entity.Stock;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketTrajectoryUtilsTest {

    //average price should be 60
    private static final List<Stock> listOfStocks = List.of(
            new Stock("One", "One", "One", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("Two", "Two", "Two", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral)
    );
    private static final List<Stock> listOfLargeCapStocks = List.of(
            new Stock("One", "One", "One", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Neutral),
            new Stock("Two", "Two", "Two", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral)
    );
    private static final List<Stock> emptyListOfStocks = Collections.emptyList();

    @Test
    void bearMarketTrajectoryTracksCorrectly() {
        assertEquals(MarketTrajectory.BEAR,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                        new Market(ZonedDateTime.now(), 100.0, MarketTrajectory.NORMAL), listOfStocks));
    }

    @Test
    void normalMarketTrajectoryTracksCorrectly() {
        assertEquals(MarketTrajectory.NORMAL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                        new Market(ZonedDateTime.now(), 100.0, MarketTrajectory.NORMAL), listOfLargeCapStocks));
        assertEquals(MarketTrajectory.NORMAL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                        new Market(ZonedDateTime.now(), 100.0, MarketTrajectory.NORMAL), emptyListOfStocks));
    }

    @Test
    void bullMarketTrajectoryTracksCorrectly() {
        assertEquals(MarketTrajectory.BULL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                        new Market(ZonedDateTime.now(), 50.0, MarketTrajectory.NORMAL), listOfLargeCapStocks));
    }

    @Test
    void priceSumTracksCorrectly() {
        assertEquals(120, MarketTrajectoryUtils.stockPricesSum(listOfStocks));
        assertEquals(0, MarketTrajectoryUtils.stockPricesSum(emptyListOfStocks));
    }

    @Test
    void averagePriceTracksCorrectly() {
        assertEquals(60, MarketTrajectoryUtils.stockPricesAverage(listOfStocks));
        assertEquals(0, MarketTrajectoryUtils.stockPricesAverage(emptyListOfStocks));
    }

}