package stocktradingsimulator.market.utils;

import org.junit.jupiter.api.Test;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.enums.MarketTrajectory;
import stocktradingsimulator.stock.enums.MarketCap;
import stocktradingsimulator.stock.model.object.DefaultStock;
import stocktradingsimulator.stock.model.entity.Stock;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketTrajectoryUtilsTest {

    //average price should be 60
    private static final List<Stock> listOfStocks = List.of(
            new DefaultStock("One", "One", "One", MarketCap.Mid, true),
            new DefaultStock("Two", "Two", "Two", MarketCap.Large, false)
    );
    private static final List<Stock> listOfLargeCapStocks = List.of(
            new DefaultStock("One", "One", "One", MarketCap.Large, true),
            new DefaultStock("Two", "Two", "Two", MarketCap.Large, false)
    );
    private static final List<Stock> emptyListOfStocks = Collections.emptyList();

    @Test
    void bearMarketTrajectoryTracksCorrectly(){
        assertEquals(MarketTrajectory.BEAR,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                    new Market( "" ,100.0, MarketTrajectory.NORMAL), listOfStocks));
    }

    @Test
    void normalMarketTrajectoryTracksCorrectly(){
        assertEquals(MarketTrajectory.NORMAL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                    new Market("" ,100.0, MarketTrajectory.NORMAL), listOfLargeCapStocks));
        assertEquals(MarketTrajectory.NORMAL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                    new Market("" ,100.0, MarketTrajectory.NORMAL), emptyListOfStocks));
    }

    @Test
    void bullMarketTrajectoryTracksCorrectly(){
        assertEquals(MarketTrajectory.BULL,
                MarketTrajectoryUtils.getNewMarketTrajectory(
                        new Market("" ,50.0, MarketTrajectory.NORMAL), listOfLargeCapStocks));
    }

    @Test
    void priceSumTracksCorrectly(){
        assertEquals(120, MarketTrajectoryUtils.stockPricesSum(listOfStocks));
        assertEquals(0, MarketTrajectoryUtils.stockPricesSum(emptyListOfStocks));
    }

    @Test
    void averagePriceTracksCorrectly(){
        assertEquals(60, MarketTrajectoryUtils.stockPricesAverage(listOfStocks));
        assertEquals(0, MarketTrajectoryUtils.stockPricesAverage(emptyListOfStocks));
    }

}