package org.api.stockmarket.stocks.stock.defaults;

import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.model.object.DefaultStock;

import java.util.List;

/*
    A list of default stocks providing the baseline data for each
    * Ticker Symbol
    * Name
    * Sector
    * Market Cap
    * Volatile or not
 */
public class DefaultStocksList {

    private static final List<DefaultStock> allStocks = List.of(
            new DefaultStock("AMZN", "Amazon", "Technology", MarketCap.Large, Volatility.VOLATILE, 1),
            new DefaultStock("AAPL", "Apple", "Technology", MarketCap.Large, Volatility.VOLATILE, 1),
            new DefaultStock("GOOG", "Google", "Technology", MarketCap.Large, Volatility.VOLATILE, 1),
            new DefaultStock("TSLA", "Tesla", "Technology", MarketCap.Large, Volatility.EXTRA_VOLATILE, -1),
            new DefaultStock("BRK.B", "Berkshire Hathaway", "Insurance", MarketCap.Large, Volatility.NORMAL, 0),
            new DefaultStock("KO", "Coca Cola", "Consumer Staples", MarketCap.Large, Volatility.STABLE, 0),
            new DefaultStock("COST", "Costco Wholesale", "Consumer Discretionary", MarketCap.Large, Volatility.STABLE, 0),
            new DefaultStock("Disney", "Walt Disney Co", "Consumer Discretionary", MarketCap.Large, Volatility.NORMAL, 0),
            new DefaultStock("WMT", "Walmart Inc", "Consumer Staples", MarketCap.Large, Volatility.STABLE, 1),
            new DefaultStock("WFC", "Wells Fargo & Co", "Finance", MarketCap.Large, Volatility.NORMAL, 0),
            new DefaultStock("SCHW", "Charles Schwab Corporation", "Finance", MarketCap.Large, Volatility.NORMAL, 0),

            new DefaultStock("SLAB", "Silicon Laboratories", "Technology", MarketCap.Mid, Volatility.VOLATILE, 0),
            new DefaultStock("GME", "GameStop Corp", "Technology", MarketCap.Mid, Volatility.EXTRA_VOLATILE, -1),
            new DefaultStock("OWL", "Big Owl Capital", "Finance", MarketCap.Mid, Volatility.NORMAL, 0),
            new DefaultStock("IDXX", "Idexx Laboratories", "Health Care", MarketCap.Mid, Volatility.VOLATILE, 0),
            new DefaultStock("DOCU", "DocuSign inc", "Technology", MarketCap.Mid, Volatility.NORMAL, 0),
            new DefaultStock("CMG", "Chipotle Mexican Grill", "Consumer Discretionary", MarketCap.Mid, Volatility.NORMAL, 0),
            new DefaultStock("MCHP", "Microchip Technology Inc", "Semiconductors", MarketCap.Mid, Volatility.VOLATILE, 0),
            new DefaultStock("CGNX", "Cognex Corp", "Technology", MarketCap.Mid, Volatility.NORMAL, 0),
            new DefaultStock("TTD", "The Trade Desk Inc", "Technology", MarketCap.Mid, Volatility.NORMAL, 0),
            new DefaultStock("X", "United States Steel Corporation", "Industrials", MarketCap.Mid, Volatility.STABLE, 1),

            new DefaultStock("NVAX", "Novavax Inc.", "BioTech", MarketCap.Small, Volatility.EXTRA_VOLATILE, 0)
    );

    public static int getCountForDefaultStocks() {
        return allStocks.size();
    }

    public static List<DefaultStock> getAllDefaultStocks() {
        return allStocks;
    }
}
