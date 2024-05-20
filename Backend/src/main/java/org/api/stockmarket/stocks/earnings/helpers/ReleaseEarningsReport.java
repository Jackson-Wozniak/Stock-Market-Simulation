package org.api.stockmarket.stocks.earnings.helpers;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.earnings.defaults.DefaultEarningsMessages;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.earnings.service.EarningsService;
import org.api.stockmarket.stocks.earnings.utils.FindEarningsPerShare;
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
                stock.increaseInvestorRating();
                stockService.updateStockInDatabase(stock);
            }else if(earningsReport.isNegativeEarnings()){
                stock.decreaseInvestorRating();
                stockService.updateStockInDatabase(stock);
            }
            earningsService.saveEarningsReport(earningsReport);
        });
    }

    public EarningsReport createEarningsReport(Stock stock, ZonedDateTime marketDate) {
        double estimatedEPS = FindEarningsPerShare.getEstimatedEarningsPerShare();
        double actualEPS = FindEarningsPerShare.getActualEarningsPerShare(stock);
        String earningsMessage = getEarningsMessage(stock, estimatedEPS, actualEPS, marketDate);
        return new EarningsReport(stock, estimatedEPS, actualEPS, earningsMessage, marketDate);
    }

    public String getEarningsMessage(Stock stock,
                                     double estimatedEPS,
                                     double actualEPS,
                                     ZonedDateTime marketDate) {
        if (actualEPS - estimatedEPS >= 1) {
            return DefaultEarningsMessages.getPositiveEarningsReport(
                    stock, estimatedEPS, actualEPS, marketDate);
        }
        if (actualEPS - estimatedEPS <= -1) {
            return DefaultEarningsMessages.getNegativeEarningsReport(
                    stock, estimatedEPS, actualEPS, marketDate);
        }
        return DefaultEarningsMessages.getNeutralEarningsReport(
                stock, estimatedEPS, actualEPS, marketDate);
    }
}
