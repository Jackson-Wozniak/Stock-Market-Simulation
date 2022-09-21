package stocktradingsimulator.market.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stocktradingsimulator.market.entity.Market;
import stocktradingsimulator.market.service.MarketService;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    @Autowired
    private final MarketService marketService;

    @RequestMapping(value = "")
    public Market findMarketEntity(){
        return marketService.findMarketEntity();
    }
}
