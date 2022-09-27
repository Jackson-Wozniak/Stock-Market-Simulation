package org.api.stockmarket.stocks.stock.utils;

import org.api.stockmarket.stocks.stock.exception.StockNotFoundException;
import org.api.stockmarket.stocks.stock.service.StockService;

public class DoesStockExist {

    public static boolean stockExistsWithTicker(StockService stockService, String ticker){
        try{
            stockService.getStockByTickerSymbol(ticker);
            return true;
        }catch(StockNotFoundException ex){
            return false;
        }
    }
}
