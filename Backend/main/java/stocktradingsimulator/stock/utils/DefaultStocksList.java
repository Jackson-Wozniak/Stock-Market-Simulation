package stocktradingsimulator.stock.utils;

import stocktradingsimulator.stock.DefaultStock;

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

    public static final List<DefaultStock> largeCapStocks = List.of(
            new DefaultStock("AMZN", "Amazon", "Technology", "Large", true),
            new DefaultStock("AAPL","Apple","Technology","Large", false),
            new DefaultStock("GOOG","Google","Technology","Large", false),
            new DefaultStock("TSLA","Tesla","Technology","Large", true),
            new DefaultStock("BRK.B","Berkshire Hathaway","Insurance","Large", false),
            new DefaultStock("KO", "Coca Cola", "Consumer Staples", "Large", false),
            new DefaultStock("COST", "Costco Wholesale", "Consumer Discretionary", "Large", false),
            new DefaultStock("Disney", "Walt Disney Co", "Consumer Discretionary", "Large", false),
            new DefaultStock("WMT", "Walmart Inc", "Consumer Staples", "Large", false),
            new DefaultStock("WFC", "Wells Fargo & Co", "Finance", "Large", false),
            new DefaultStock("SCHW", "Charles Schwab Corporation", "Finance", "Large", false)
    );
}
