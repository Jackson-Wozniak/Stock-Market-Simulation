package stocktradingsimulator.market;

import stocktradingsimulator.stock.Stock;

public class ChangeStockPrices {

    public static double automaticPriceChange(Stock stock){
        return switch (stock.getMarketCap().toLowerCase()){
            case "large" -> changeLargeCapPrice(stock);
            case "mid" -> changeMidCapPrice(stock);
            case "small" -> changeSmallCapPrice(stock);
            default -> stock.getPrice();
        };
    }

    private static double changeLargeCapPrice(Stock stock){
        return stock.getPrice() + (stock.getPrice() * .002);
    }

    private static double changeMidCapPrice(Stock stock){
        return stock.getPrice() + (stock.getPrice() * .001);
    }

    private static double changeSmallCapPrice(Stock stock){
        return stock.getPrice() + (stock.getPrice() * .0005);
    }
}
