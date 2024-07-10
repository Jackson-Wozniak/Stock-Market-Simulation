package org.api.stockmarket.stocks.stock.properties;

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
            Stock.largeCap("AMZN", "Amazon", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("AAPL", "Apple", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("GOOG", "Google", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("TSLA", "Tesla", "Technology", Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            Stock.largeCap("BRK.B", "Berkshire Hathaway", "Insurance", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.largeCap("KO", "Coca Cola", "Consumer Staples", Volatility.STABLE, InvestorRating.Neutral),
            Stock.largeCap("COST", "Costco Wholesale", "Consumer Discretionary", Volatility.STABLE, InvestorRating.Neutral),
            Stock.largeCap("Disney", "Walt Disney Co", "Consumer Discretionary", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.largeCap("WMT", "Walmart Inc", "Consumer Staples", Volatility.STABLE, InvestorRating.Buy),
            Stock.largeCap("WFC", "Wells Fargo & Co", "Finance", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.largeCap("SCHW", "Charles Schwab Corporation", "Finance", Volatility.NORMAL, InvestorRating.Neutral),

            Stock.midCap("SLAB", "Silicon Laboratories", "Technology", Volatility.VOLATILE, InvestorRating.Neutral),
            Stock.midCap("GME", "GameStop Corp", "Technology", Volatility.EXTRA_VOLATILE, InvestorRating.Hold),
            Stock.midCap("OWL", "Big Owl Capital", "Finance", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.midCap("IDXX", "Idexx Laboratories", "Health Care", Volatility.VOLATILE, InvestorRating.Neutral),
            Stock.midCap("DOCU", "DocuSign inc", "Technology", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.midCap("CMG", "Chipotle Mexican Grill", "Consumer Discretionary", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.midCap("MCHP", "Microchip Technology Inc", "Semiconductors", Volatility.VOLATILE, InvestorRating.Neutral),
            Stock.midCap("CGNX", "Cognex Corp", "Technology", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.midCap("TTD", "The Trade Desk Inc", "Technology", Volatility.NORMAL, InvestorRating.Neutral),
            Stock.midCap("X", "United States Steel Corporation", "Industrials", Volatility.STABLE, InvestorRating.Buy),

            Stock.smallCap("NVAX", "Novavax Inc.", "BioTech", Volatility.EXTRA_VOLATILE, InvestorRating.Neutral),
            Stock.smallCap("PRTS", "CarParts.com Inc", "Consumer Discretionary", Volatility.VOLATILE, InvestorRating.Neutral)
    );

    public static int getCountForDefaultStocks() {
        return allStocks.size();
    }

    public static List<Stock> getAllDefaultStocks() {
        return allStocks;
    }
}
