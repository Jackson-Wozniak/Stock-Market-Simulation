package org.api.stockmarket.indexfund.helper;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.utils.MarketTrajectoryUtils;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalculateIndexFundPrice {

    @Autowired
    private StockService stockService;

    public double findPriceOfTotalMarketFund() {
        return MarketTrajectoryUtils.stockPricesAverage(stockService.getAllStocks());
    }

    public double findPriceOfMarketCapFund(MarketCap marketCap) {
        return MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocksByMarketCap(marketCap));
    }

    public double findPriceOfSectorFund(String sector) {
        return MarketTrajectoryUtils.stockPricesAverage(stockService.getAllStocksBySector(sector));
    }

    public double findPriceOfVolatileFunds(Volatility volatility) {
        return MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocksByVolatility(volatility));
    }
}
