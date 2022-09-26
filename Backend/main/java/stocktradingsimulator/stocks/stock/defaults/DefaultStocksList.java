package stocktradingsimulator.stocks.stock.defaults;

import stocktradingsimulator.stocks.stock.enums.MarketCap;
import stocktradingsimulator.stocks.stock.model.object.DefaultStock;

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
            new DefaultStock("AMZN", "Amazon", "Technology", MarketCap.Large, true),
            new DefaultStock("AAPL","Apple","Technology",MarketCap.Large, false),
            new DefaultStock("GOOG","Google","Technology",MarketCap.Large, false),
            new DefaultStock("TSLA","Tesla","Technology",MarketCap.Large, true),
            new DefaultStock("BRK.B","Berkshire Hathaway","Insurance",MarketCap.Large, false),
            new DefaultStock("KO", "Coca Cola", "Consumer Staples", MarketCap.Large, false),
            new DefaultStock("COST", "Costco Wholesale", "Consumer Discretionary", MarketCap.Large, false),
            new DefaultStock("Disney", "Walt Disney Co", "Consumer Discretionary", MarketCap.Large, false),
            new DefaultStock("WMT", "Walmart Inc", "Consumer Staples", MarketCap.Large, false),
            new DefaultStock("WFC", "Wells Fargo & Co", "Finance", MarketCap.Large, false),
            new DefaultStock("SCHW", "Charles Schwab Corporation", "Finance", MarketCap.Large, false),

            new DefaultStock("SLAB", "Silicon Laboratories", "Technology", MarketCap.Mid, false),
            new DefaultStock("GME", "GameStop Corp", "Technology", MarketCap.Mid, true),
            new DefaultStock("OWL", "Big Owl Capital", "Finance", MarketCap.Mid, false),

            new DefaultStock("NVAX", "Novavax Inc.", "BioTech", MarketCap.Small, true)
    );

    public static int getCountForDefaultStocks(){
        return allStocks.size();
    }

    public static List<DefaultStock> getAllDefaultStocks(){
        return allStocks;
    }
}
