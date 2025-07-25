package org.api.stockmarket.stocks.news.factory;

import org.api.stockmarket.stocks.news.entity.EarningsReport;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class EarningsFactory {

    public EarningsReport generate(Stock stock, ZonedDateTime marketDate){
        return new EarningsReport(stock, marketDate);
    }
}
