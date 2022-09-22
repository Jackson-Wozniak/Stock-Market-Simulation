package stocktradingsimulator.stock.defaults;

public class DefaultStockPrices {

    public static final double DEFAULT_LARGE_CAP_PRICE = 100.0;
    public static final double DEFAULT_MID_CAP_PRICE = 20.0;
    public static final double DEFAULT_SMALL_CAP_PRICE = 5.0;

    public static double getDefaultPriceWithCap(String marketCap){
        marketCap = marketCap.toLowerCase();
        return switch (marketCap) {
            case "large" -> DEFAULT_LARGE_CAP_PRICE;
            case "mid" -> DEFAULT_MID_CAP_PRICE;
            case "small" -> DEFAULT_SMALL_CAP_PRICE;
            default -> 0.0;
        };
    }
}
