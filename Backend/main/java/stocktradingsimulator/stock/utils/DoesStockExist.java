package stocktradingsimulator.stock.utils;

import stocktradingsimulator.stock.StockNotFoundException;
import stocktradingsimulator.stock.StockService;

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
