package org.api.stockmarket.stocks.earnings.helpers;

import lombok.AllArgsConstructor;
import org.api.stockmarket.stocks.earnings.defaults.DefaultEarningsMessages;
import org.api.stockmarket.stocks.earnings.entity.EarningsReport;
import org.api.stockmarket.stocks.earnings.service.EarningsService;
import org.api.stockmarket.stocks.earnings.utils.FindEarningsPerShare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.api.stockmarket.stocks.stock.model.entity.Stock;

import java.util.List;

/*
This class controls the release and saving of earnings reports every 3 market months
 */
@Component
@AllArgsConstructor
public class ReleaseEarningsReport {

    @Autowired
    private final EarningsService earningsService;

    public void handleQuarterlyEarningsReports(List<Stock> stocks, String marketDate){
        stocks.forEach(stock -> earningsService.saveEarningsReport(
                createEarningsReport(stock, marketDate)));
    }

    public EarningsReport createEarningsReport(Stock stock, String marketDate){
        double estimatedEPS = FindEarningsPerShare.getEstimatedEarningsPerShare();
        double actualEPS = FindEarningsPerShare.getActualEarningsPerShare(stock);
        String earningsMessage = getEarningsMessage(stock, estimatedEPS, actualEPS, marketDate);
        return new EarningsReport(stock, estimatedEPS, actualEPS, earningsMessage, marketDate);
    }

    public String getEarningsMessage(Stock stock,
                                     double estimatedEPS,
                                     double actualEPS,
                                     String marketDate){
        if(actualEPS - estimatedEPS >= 1){
           return DefaultEarningsMessages.getPositiveEarningsReport(
                   stock, estimatedEPS, actualEPS, marketDate);
        }
        if(actualEPS - estimatedEPS <= -1){
            return DefaultEarningsMessages.getNegativeEarningsReport(
                    stock, estimatedEPS, actualEPS, marketDate);
        }
        return DefaultEarningsMessages.getNeutralEarningsReport(
                stock, estimatedEPS, actualEPS, marketDate);
    }
}
