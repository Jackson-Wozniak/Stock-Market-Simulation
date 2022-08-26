package stocktradingsimulator.market;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@AllArgsConstructor
public class ConfigureMarketActivity {

    @Autowired
    private HandleMarketActivity handleMarketActivity;

    //@Scheduled(fixedDelay = 5000L)
    public void marketActivity(){
        handleMarketActivity.printPrices();
    }
}
