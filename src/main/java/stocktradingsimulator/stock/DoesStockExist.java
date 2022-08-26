package stocktradingsimulator.stock;

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
