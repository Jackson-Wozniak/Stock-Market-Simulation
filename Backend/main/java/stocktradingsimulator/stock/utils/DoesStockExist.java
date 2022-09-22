package stocktradingsimulator.stock.utils;

import stocktradingsimulator.stock.exception.StockNotFoundException;
import stocktradingsimulator.stock.service.StockService;

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
