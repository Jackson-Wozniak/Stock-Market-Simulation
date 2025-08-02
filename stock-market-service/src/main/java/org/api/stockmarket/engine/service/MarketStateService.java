package org.api.stockmarket.engine.service;

import java.time.ZonedDateTime;
import java.util.Optional;

import org.api.stockmarket.common.exceptions.ConfigurationException;
import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.entity.MarketSingletonEntity;
import org.api.stockmarket.engine.repository.MarketSingletonRepository;
import org.springframework.stereotype.Service;

import static org.api.stockmarket.engine.properties.MarketEnvironmentProperties.*;

@Service
public class MarketStateService {
    private final MarketSingletonRepository marketRepository;
    private MarketSingletonEntity cachedMarketSingleton;

    public MarketStateService(MarketSingletonRepository marketSingletonRepository) {
        this.marketRepository = marketSingletonRepository;
        this.cachedMarketSingleton = findOrRegisterMarket(true);
    }

    private MarketSingletonEntity findOrRegisterMarket(boolean isConstructor){
        if(cachedMarketSingleton != null) return cachedMarketSingleton;
        Optional<MarketSingletonEntity> market = marketRepository.findById(MARKET_SINGLETON_ID);
        if(market.isEmpty() && !isConstructor){
            return ConfigurationException.failAndExitAs("Market state was not in cache or in DB", getClass().getName());
        }
        cachedMarketSingleton = marketRepository.save(MarketSingletonEntity.getDefault());
        return cachedMarketSingleton;
    }

    public MarketState findMarketState(){
        return findOrRegisterMarket(false).getState();
    }

    public MarketState incrementAndSave(){
        MarketSingletonEntity market = findOrRegisterMarket(false);
        ZonedDateTime time = market.getDate();
        time = time.plusHours(1);
        cachedMarketSingleton.setDate(time);
        cachedMarketSingleton = marketRepository.save(cachedMarketSingleton);
        return cachedMarketSingleton.getState();
    }
}
