package stocktradingsimulator.market;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.market.entity.Market;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    @Autowired
    private final Market market;

    @RequestMapping(value = "")
    public Market findMarketEntity(){
        return market;
    }
}
