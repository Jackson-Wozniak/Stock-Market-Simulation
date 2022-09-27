package org.api.stockmarket.market.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.enums.MarketTrajectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.api.stockmarket.market.repository.MarketRepository;

@Service
@AllArgsConstructor
public class MarketService {

    @Autowired
    private final MarketRepository marketRepository;

    public Market findMarketEntity(){
        Market market = marketRepository.findById(1).orElse(null);
        if(market == null){
            market = new Market("1/1/1" ,41.0, MarketTrajectory.NORMAL);
            saveMarketEntity(market);
            return market;
        }
        return market;
    }

    public void saveMarketEntity(Market market){
        if(market == null) return;
        marketRepository.save(market);
    }
}
