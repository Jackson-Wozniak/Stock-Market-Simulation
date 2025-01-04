package org.api.stockmarket.stocks.news.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.news.entity.EarningsReport;
import org.api.stockmarket.stocks.news.factory.EarningsFactory;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EarningsManager {

    private final EarningsService earningsService;
    private final StockService stockService;
    private final EarningsFactory earningsFactory;

    public void handleQuarterlyEarningsReports(List<Stock> stocks, ZonedDateTime marketDate) {
        stocks.forEach(stock -> {
            EarningsReport earningsReport = earningsFactory.generate(stock, marketDate);
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
}
