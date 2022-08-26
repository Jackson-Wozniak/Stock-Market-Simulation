package stocktradingsimulator.stock;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        stockService.saveDefaultStockToDatabase(DefaultStocks.largeCapStocks);
    }
}
