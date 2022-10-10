package org.api.stockmarket.indexfund.helper;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.defaults.DefaultIndexFunds;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.indexfund.model.subclass.SectorIndexFund;
import org.api.stockmarket.market.utils.MarketTrajectoryUtils;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CalculateIndexFundPrice {

    @Autowired
    private StockService stockService;

    public List<MarketCapIndexFund> updateMarketCapIndexFunds(){
        List<MarketCapIndexFund> funds = DefaultIndexFunds.marketCapIndexFunds;
        funds.forEach(fund -> fund.setPrice(findPriceOfMarketCapFund(fund.getMarketCap())));
        return funds;
    }

    public List<SectorIndexFund> updateSectorIndexFunds(){
        List<SectorIndexFund> funds = DefaultIndexFunds.sectorIndexFunds;
        funds.forEach(fund -> fund.setPrice(findPriceOfSectorFund(fund.getSector())));
        System.out.println(findPriceOfSectorFund("Technology"));
        return funds;
    }

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

    public double findPriceOfVolatileFunds(boolean volatility) {
        return MarketTrajectoryUtils.stockPricesAverage(
                stockService.getAllStocksByVolatility(volatility));
    }
}
