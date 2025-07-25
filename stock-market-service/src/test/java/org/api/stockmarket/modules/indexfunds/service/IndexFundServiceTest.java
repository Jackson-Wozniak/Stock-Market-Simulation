package org.api.stockmarket.indexfund.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.indexfunds.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.modules.indexfunds.service.IndexFundService;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.MarketCap;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.api.stockmarket.modules.stocks.properties.DefaultStockPrices;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class IndexFundServiceTest {

    private final List<Stock> DIFFERENT_VOLATILITIES = List.of(
            Stock.largeCap("-", "-", "-", Volatility.EXTRA_VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "-", Volatility.STABLE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "-", Volatility.NORMAL, InvestorRating.Buy)
    );

    private final List<Stock> SAME_VOLATILITIES = List.of(
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.midCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.smallCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy)
    );

    private final List<Stock> DIFFERENT_SECTORS = List.of(
            Stock.largeCap("-", "-", "1", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "2", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "3", Volatility.VOLATILE, InvestorRating.Buy)
    );

    private final List<Stock> SAME_SECTORS = List.of(
            Stock.largeCap("-", "-", "1", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.midCap("-", "-", "1", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.smallCap("-", "-", "1", Volatility.VOLATILE, InvestorRating.Buy)
    );

    private final List<Stock> DIFFERENT_CAPS = List.of(
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.midCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.smallCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy)
    );

    private final List<Stock> LARGE_CAPS = List.of(
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy),
            Stock.largeCap("-", "-", "-", Volatility.VOLATILE, InvestorRating.Buy)
    );

    @Test
    void testMarketCapFunds() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocks()).thenReturn(DIFFERENT_CAPS);
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
    void testMarketCapFund() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocksByMarketCap(MarketCap.Large)).thenReturn(LARGE_CAPS);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        IndexFund fund = indexFundService.findMarketCapFunds(MarketCap.Large);

        MarketCapIndexFund cap = (MarketCapIndexFund) fund;
        assertEquals(DefaultStockPrices.getDefaultPrice(cap.getMarketCap()), fund.getPrice());

        verify(mockStockService, times(1)).getAllStocksByMarketCap(MarketCap.Large);
    }

    @Test
    void testSectorFunds() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocks()).thenReturn(DIFFERENT_SECTORS);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        List<IndexFund> funds = indexFundService.findSectorFunds();

        //all mock stocks are large cap (100 price)
        funds.forEach(fund -> assertEquals(100.0, fund.getPrice()));

        verify(mockStockService, times(1)).getAllStocks();
    }

    @Test
    void testSectorFund() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocksBySector("1")).thenReturn(SAME_SECTORS);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        IndexFund fund = indexFundService.findSectorFunds("1");

        //41.67 is average of one stock of each cap
        assertEquals(41.67, fund.getPrice());

        verify(mockStockService, times(1)).getAllStocksBySector("1");
    }

    @Test
    void testVolatilityFunds() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocks()).thenReturn(DIFFERENT_VOLATILITIES);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        List<IndexFund> funds = indexFundService.findVolatilityFunds();

        //all mock stocks are large cap (100 price)
        funds.forEach(fund -> assertEquals(100.0, fund.getPrice()));

        verify(mockStockService, times(1)).getAllStocks();
    }

    @Test
    void testVolatilityFund() {
        StockService mockStockService = Mockito.mock(StockService.class);

        when(mockStockService.getAllStocksByVolatility(Volatility.VOLATILE)).thenReturn(SAME_VOLATILITIES);
        IndexFundService indexFundService = new IndexFundService(mockStockService);
        IndexFund fund = indexFundService.findVolatilityFunds(Volatility.VOLATILE);

        //41.67 is average of one stock of each cap
        assertEquals(41.67, fund.getPrice());

        verify(mockStockService, times(1)).getAllStocksByVolatility(Volatility.VOLATILE);
    }
}
