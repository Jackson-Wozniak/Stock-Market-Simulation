package stocktradingsimulator.stock;

import java.util.List;

public class DefaultStocks {

    private List<Stock> largeCapStocks = List.of(
            new Stock("AMZN", "Amazon", "Technology", "Large", true),
            new Stock("AAPL","Apple","Technology","Large", false)
    );
}
