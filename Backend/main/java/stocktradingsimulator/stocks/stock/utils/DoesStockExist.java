package stocktradingsimulator.stocks.stock.utils;

import stocktradingsimulator.stocks.stock.exception.StockNotFoundException;
import stocktradingsimulator.stocks.stock.service.StockService;

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
