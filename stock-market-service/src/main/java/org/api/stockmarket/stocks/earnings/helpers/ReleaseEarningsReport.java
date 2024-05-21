package org.api.stockmarket.stocks.earnings.helpers;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.earnings.service.EarningsService;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

/*
This class controls the release and saving of earnings reports every 3 market months
 */
@Component
@AllArgsConstructor
public class ReleaseEarningsReport {

    @Autowired
    private final EarningsService earningsService;
    @Autowired
    private final StockService stockService;

    public void handleQuarterlyEarningsReports(List<Stock> stocks, ZonedDateTime marketDate) {
        stocks.forEach(stock -> {
            EarningsReport earningsReport = createEarningsReport(stock, marketDate);
            if(earningsReport.isPositiveEarnings()){
                stock.newsEvent(true);
                stockService.updateStockInDatabase(stock);
            }else if(earningsReport.isNegativeEarnings()){
                stock.newsEvent(false);
                stockService.updateStockInDatabase(stock);
            }
            earningsService.saveEarningsReport(earningsReport);
        });
    }

    public EarningsReport createEarningsReport(Stock stock, ZonedDateTime marketDate) {
        return new EarningsReport(stock, marketDate);
    }
}
