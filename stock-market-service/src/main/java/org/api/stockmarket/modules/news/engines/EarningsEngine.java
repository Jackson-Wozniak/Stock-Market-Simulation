package org.api.stockmarket.modules.news.engines;

import lombok.AllArgsConstructor;
import org.api.stockmarket.modules.news.entity.EarningsReport;
import org.api.stockmarket.modules.news.factory.EarningsFactory;
import org.api.stockmarket.modules.news.service.EarningsService;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.service.StockService;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EarningsEngine {

//    private final EarningsService earningsService;
//    private final StockService stockService;
//    private final EarningsFactory earningsFactory;
//
//    public void handleQuarterlyEarningsReports(List<Stock> stocks, ZonedDateTime marketDate) {
//        stocks.forEach(stock -> {
//            EarningsReport earningsReport = earningsFactory.generate(stock, marketDate);
//            if(earningsReport.isPositiveEarnings()){
//                stock.newsEvent(true);
//                stockService.updateStockInDatabase(stock);
//            }else if(earningsReport.isNegativeEarnings()){
//                stock.newsEvent(false);
//                stockService.updateStockInDatabase(stock);
//            }
//            earningsService.saveEarningsReport(earningsReport);
//        });
//    }
}
