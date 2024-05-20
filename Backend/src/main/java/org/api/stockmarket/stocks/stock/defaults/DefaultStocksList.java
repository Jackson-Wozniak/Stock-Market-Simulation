package org.api.stockmarket.stocks.stock.defaults;

import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.entity.Stock;

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

    private static final List<Stock> allStocks = List.of(
            new Stock("AMZN", "Amazon", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new Stock("AAPL", "Apple", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new Stock("GOOG", "Google", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new Stock("TSLA", "Tesla", "Technology", MarketCap.Large, Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            new Stock("BRK.B", "Berkshire Hathaway", "Insurance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("KO", "Coca Cola", "Consumer Staples", MarketCap.Large, Volatility.STABLE, InvestorRating.Neutral),
            new Stock("COST", "Costco Wholesale", "Consumer Discretionary", MarketCap.Large, Volatility.STABLE, InvestorRating.Neutral),
            new Stock("Disney", "Walt Disney Co", "Consumer Discretionary", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("WMT", "Walmart Inc", "Consumer Staples", MarketCap.Large, Volatility.STABLE, InvestorRating.Buy),
            new Stock("WFC", "Wells Fargo & Co", "Finance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("SCHW", "Charles Schwab Corporation", "Finance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),

            new Stock("SLAB", "Silicon Laboratories", "Technology", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new Stock("GME", "GameStop Corp", "Technology", MarketCap.Mid, Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            new Stock("OWL", "Big Owl Capital", "Finance", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("IDXX", "Idexx Laboratories", "Health Care", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new Stock("DOCU", "DocuSign inc", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("CMG", "Chipotle Mexican Grill", "Consumer Discretionary", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("MCHP", "Microchip Technology Inc", "Semiconductors", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new Stock("CGNX", "Cognex Corp", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("TTD", "The Trade Desk Inc", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new Stock("X", "United States Steel Corporation", "Industrials", MarketCap.Mid, Volatility.STABLE, InvestorRating.Buy),

            new Stock("NVAX", "Novavax Inc.", "BioTech", MarketCap.Small, Volatility.EXTRA_VOLATILE, InvestorRating.Neutral),
            new Stock("PRTS", "CarParts.com Inc", "Consumer Discretionary", MarketCap.Small, Volatility.VOLATILE, InvestorRating.Neutral)
    );

    public static int getCountForDefaultStocks() {
        return allStocks.size();
    }

    public static List<Stock> getAllDefaultStocks() {
        return allStocks;
    }
}
