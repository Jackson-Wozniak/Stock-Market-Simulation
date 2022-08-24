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
            new Stock("AAPL","Apple","Technology","Large", false)
    );
}
