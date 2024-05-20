package org.api.stockmarket.stocks.stock.defaults;

import org.api.stockmarket.stocks.stock.entity.LargeCapStock;
import org.api.stockmarket.stocks.stock.entity.MidCapStock;
import org.api.stockmarket.stocks.stock.entity.SmallCapStock;
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
            new LargeCapStock("AMZN", "Amazon", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new LargeCapStock("AAPL", "Apple", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new LargeCapStock("GOOG", "Google", "Technology", MarketCap.Large, Volatility.VOLATILE, InvestorRating.Buy),
            new LargeCapStock("TSLA", "Tesla", "Technology", MarketCap.Large, Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            new LargeCapStock("BRK.B", "Berkshire Hathaway", "Insurance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new LargeCapStock("KO", "Coca Cola", "Consumer Staples", MarketCap.Large, Volatility.STABLE, InvestorRating.Neutral),
            new LargeCapStock("COST", "Costco Wholesale", "Consumer Discretionary", MarketCap.Large, Volatility.STABLE, InvestorRating.Neutral),
            new LargeCapStock("Disney", "Walt Disney Co", "Consumer Discretionary", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new LargeCapStock("WMT", "Walmart Inc", "Consumer Staples", MarketCap.Large, Volatility.STABLE, InvestorRating.Buy),
            new LargeCapStock("WFC", "Wells Fargo & Co", "Finance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),
            new LargeCapStock("SCHW", "Charles Schwab Corporation", "Finance", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral),

            new MidCapStock("SLAB", "Silicon Laboratories", "Technology", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new MidCapStock("GME", "GameStop Corp", "Technology", MarketCap.Mid, Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            new MidCapStock("OWL", "Big Owl Capital", "Finance", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new MidCapStock("IDXX", "Idexx Laboratories", "Health Care", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new MidCapStock("DOCU", "DocuSign inc", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new MidCapStock("CMG", "Chipotle Mexican Grill", "Consumer Discretionary", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new MidCapStock("MCHP", "Microchip Technology Inc", "Semiconductors", MarketCap.Mid, Volatility.VOLATILE, InvestorRating.Neutral),
            new MidCapStock("CGNX", "Cognex Corp", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new MidCapStock("TTD", "The Trade Desk Inc", "Technology", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral),
            new MidCapStock("X", "United States Steel Corporation", "Industrials", MarketCap.Mid, Volatility.STABLE, InvestorRating.Buy),

            new SmallCapStock("NVAX", "Novavax Inc.", "BioTech", MarketCap.Small, Volatility.EXTRA_VOLATILE, InvestorRating.Neutral),
            new SmallCapStock("PRTS", "CarParts.com Inc", "Consumer Discretionary", MarketCap.Small, Volatility.VOLATILE, InvestorRating.Neutral)
    );

    public static int getCountForDefaultStocks() {
        return allStocks.size();
    }

    public static List<Stock> getAllDefaultStocks() {
        return allStocks;
    }
}
