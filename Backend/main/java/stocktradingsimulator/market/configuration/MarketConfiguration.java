package stocktradingsimulator.market.configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.enums.MarketTrajectory;
import stocktradingsimulator.market.repository.MarketRepository;

@Configuration
@AllArgsConstructor
public class MarketConfiguration {

    @Autowired
    private final MarketRepository marketRepository;

    /*
        Create bean for a single market entity.
        This entity acts as the decider of the trajectory of
        the total market by saving and comparing average prices of stocks
        across all sectors/caps.
        Visit MarketTrajectory.java for specifics on % changes
     */
    @Bean
    public Market market() throws Exception {
        return marketRepository.findById(1)
                .orElse(marketRepository.save(
                        new Market(100.0, MarketTrajectory.NORMAL)));
    }
}
