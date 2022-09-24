package stocktradingsimulator.indexfund.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import stocktradingsimulator.market.utils.MarketTrajectoryUtils;
import stocktradingsimulator.stock.service.StockService;

@Component
@AllArgsConstructor
public class CalculateIndexFundPrice {

    @Autowired
    private StockService stockService;

    public double findPriceOfTotalMarketFund(){
        return MarketTrajectoryUtils.stockPricesAverage(stockService.getAllStocks());
    }

    public double findPriceOfMarketCapFund(String marketCap){
        return MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocksByMarketCap(marketCap));
    }

    public double findPriceOfSectorFund(String sector){
        return MarketTrajectoryUtils.stockPricesAverage(stockService.getAllStocksBySector(sector));
    }

    public double findPriceOfVolatileFunds(boolean volatility){
        return MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocksByVolatility(volatility));
    }
}
