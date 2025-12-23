package org.api.stockmarket.engine.service;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.api.stockmarket.core.exception.ConfigurationException;
import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.entity.MarketSingletonEntity;
import org.api.stockmarket.engine.enums.CurrentTimeRange;
import org.api.stockmarket.engine.repository.MarketSingletonRepository;
import org.springframework.stereotype.Service;

import static org.api.stockmarket.engine.properties.MarketEnvironmentProperties.*;

@Service
public class MarketStateService {
    private final MarketSingletonRepository marketRepository;
    private MarketSingletonEntity cachedMarketSingleton;
    private AtomicBoolean isMarketRunning = new AtomicBoolean(false);
    private AtomicLong currentIntervalMs = new AtomicLong(0);

    public MarketStateService(MarketSingletonRepository marketSingletonRepository) {
        this.marketRepository = marketSingletonRepository;
        this.cachedMarketSingleton = findOrRegisterMarket(true);
    }

    public void updateSchedulingConfig(boolean isRunning, long intervalMs){
        this.isMarketRunning = new AtomicBoolean(isRunning);
        this.currentIntervalMs = new AtomicLong(intervalMs);
    }

    private MarketSingletonEntity findOrRegisterMarket(boolean isConstructor){
        if(cachedMarketSingleton != null) return cachedMarketSingleton;
        Optional<MarketSingletonEntity> market = marketRepository.findById(MARKET_SINGLETON_ID);
        if(market.isPresent()){
            cachedMarketSingleton = market.get();
            return cachedMarketSingleton;
        }
        if(!isConstructor){
            return ConfigurationException.failAndExitAs("Market state was not in cache or in DB", getClass().getName());
        }
        cachedMarketSingleton = marketRepository.save(MarketSingletonEntity.getDefault());
        return cachedMarketSingleton;
    }

    public MarketState findMarketState(){
        return findOrRegisterMarket(false).getState(isMarketRunning.get(), currentIntervalMs.get());
    }

    public MarketState incrementAndGet(){
        MarketSingletonEntity priorMarket = findOrRegisterMarket(false);

        CurrentTimeRange priorRange = priorMarket.getTimeRange();
        ZonedDateTime currentTime = priorMarket.getDate();

        ZonedDateTime newTime = currentTime.plusMinutes(ADDED_MINUTES_PER_RUN);

        cachedMarketSingleton.setDate(newTime);
        cachedMarketSingleton = marketRepository.save(cachedMarketSingleton);
        return cachedMarketSingleton.getState(priorRange, isMarketRunning.get(), currentIntervalMs.get());
    }

    public MarketState incrementAfterHours(){
        MarketSingletonEntity priorMarket = findOrRegisterMarket(false);

        ZonedDateTime currentTime = priorMarket.getDate();

        ZonedDateTime newTime = currentTime.plusHours(1);

        cachedMarketSingleton.setDate(newTime);
        cachedMarketSingleton = marketRepository.save(cachedMarketSingleton);
        return cachedMarketSingleton.getState(isMarketRunning.get(), currentIntervalMs.get());
    }

    public void cleanCache(){
        cachedMarketSingleton = null;
    }
}
