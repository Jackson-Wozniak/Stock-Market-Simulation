package org.api.stockmarket.indexfund.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.properties.DefaultStockPrices;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class IndexFundServiceTest {

    @Test
    void testMarketCapFundsWithMockedStocks() {
        StockService mockStockService = Mockito.mock(StockService.class);

        List<Stock> mockStocks = List.of(
                Stock.largeCap("AMZN", "Amazon", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
                Stock.midCap("AAPL", "Apple", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
                Stock.smallCap("GOOG", "Google", "Technology", Volatility.VOLATILE, InvestorRating.Buy)
        );

        when(mockStockService.getAllStocks()).thenReturn(mockStocks);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        List<IndexFund> indexFunds = indexFundService.findMarketCapFunds();

        assertEquals(3, indexFunds.size());

        indexFunds.forEach(fund -> {
            MarketCapIndexFund cap = (MarketCapIndexFund) fund;
            assertEquals(DefaultStockPrices.getDefaultPrice(cap.getMarketCap()), fund.getPrice());
        });

        verify(mockStockService, times(1)).getAllStocks();
    }

    @Test
    void testMarketCapFundWithMockedStocks() {
        StockService mockStockService = Mockito.mock(StockService.class);

        List<Stock> mockStocks = List.of(
                Stock.largeCap("AMZN", "Amazon", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
                Stock.largeCap("AAPL", "Apple", "Technology", Volatility.VOLATILE, InvestorRating.Buy),
                Stock.largeCap("GOOG", "Google", "Technology", Volatility.VOLATILE, InvestorRating.Buy)
        );

        when(mockStockService.getAllStocksByMarketCap(MarketCap.Large)).thenReturn(mockStocks);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        IndexFund fund = indexFundService.findMarketCapFunds(MarketCap.Large);

        MarketCapIndexFund cap = (MarketCapIndexFund) fund;
        assertEquals(DefaultStockPrices.getDefaultPrice(cap.getMarketCap()), fund.getPrice());

        verify(mockStockService, times(1)).getAllStocks();
    }
}
