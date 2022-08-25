package stocktradingsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stocktradingsimulator.stock.SaveDefaultStocks;

@SpringBootApplication
public class StocktradingsimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(StocktradingsimulatorApplication.class, args);
	}

}
