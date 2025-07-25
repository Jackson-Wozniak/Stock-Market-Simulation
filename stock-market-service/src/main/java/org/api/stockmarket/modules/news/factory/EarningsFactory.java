package org.api.stockmarket.modules.news.factory;

import org.api.stockmarket.modules.news.entity.EarningsReport;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
public class EarningsFactory {

    public EarningsReport generate(Stock stock, ZonedDateTime marketDate){
        return new EarningsReport(stock, marketDate);
    }
}
