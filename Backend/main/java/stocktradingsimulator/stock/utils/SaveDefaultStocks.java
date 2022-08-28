package stocktradingsimulator.stock.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import stocktradingsimulator.stock.StockService;

@Configuration
@AllArgsConstructor
public class SaveDefaultStocks {

    @Autowired
    private final StockService stockService;

    //uncomment when needing to save default stocks
//    @Bean
//    CommandLineRunner runner(){
//        return args -> {
//          saveLargeCapStocks();
//        };
//    }

    public void saveLargeCapStocks(){
        stockService.saveDefaultStockToDatabase(DefaultStocksList.largeCapStocks);
    }
}
