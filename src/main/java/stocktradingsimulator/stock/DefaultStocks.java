package stocktradingsimulator.stock;

import java.util.List;

/*
    A list of default stocks providing the baseline data for each
    * Ticker Symbol
    * Name
    * Sector
    * Market Cap
    * Volatile or not
 */
public class DefaultStocks {

    public static final List<Stock> largeCapStocks = List.of(
            new Stock("AMZN", "Amazon", "Technology", "Large", true),
            new Stock("AAPL","Apple","Technology","Large", false),
            new Stock("GOOG","Google","Technology","Large", false),
            new Stock("TSLA","Tesla","Technology","Large", true),
            new Stock("BRK.B","Berkshire Hathaway","Insurance","Large", false),
            new Stock("KO", "Coca Cola", "Consumer Staples", "Large", false),
            new Stock("COST", "Costco Wholesale", "Consumer Discretionary", "Large", false),
            new Stock("Disney", "Walt Disney Co", "Consumer Discretionary", "Large", false),
            new Stock("WMT", "Walmart Inc", "Consumer Staples", "Large", false),
            new Stock("WFC", "Wells Fargo & Co", "Finance", "Large", false),
            new Stock("SCHW", "Charles Schwab Corporation", "Finance", "Large", false)
    );
}
