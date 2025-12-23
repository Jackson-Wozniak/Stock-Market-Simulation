package org.api.stockmarket.engine.service;

import org.api.stockmarket.engine.entity.MarketState;
import org.api.stockmarket.engine.enums.MarketSentiment;
import org.api.stockmarket.engine.enums.TemporalMarketMilestone;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;
import org.api.stockmarket.engine.repository.MarketSingletonRepository;
import org.api.stockmarket.engine.scheduling.MarketActivityScheduler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MarketStateServiceTest {
    @Autowired
    private MarketStateService marketStateService;

    @BeforeEach
    void resetService(){
        marketStateService.cleanCache();
    }

    @Test
    void findMarketState_calledOnStartup_returnsDefault(){
        MarketState state = marketStateService.findMarketState();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE.toInstant(), state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());
    }

    @Test
    void incrementAndGet_calledAfterStartup_incrementsOnce(){
        MarketState state = marketStateService.findMarketState();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE.toInstant(), state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());

        state = marketStateService.incrementAndGet();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE
                .plusMinutes(MarketEnvironmentProperties.ADDED_MINUTES_PER_RUN).toInstant(),
                state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());
    }

    @Test
    void incrementAfterHours_calledAfterIncrement_incrementsCorrectly(){
        MarketState state = marketStateService.findMarketState();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE.toInstant(), state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());

        state = marketStateService.incrementAndGet();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE
                        .plusMinutes(MarketEnvironmentProperties.ADDED_MINUTES_PER_RUN).toInstant(),
                state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());

        state = marketStateService.incrementAfterHours();
        assertNotNull(state);
        assertEquals(MarketEnvironmentProperties.STARTING_MARKET_DATE
                        .plusMinutes(MarketEnvironmentProperties.ADDED_MINUTES_PER_RUN)
                        .plusHours(1).toInstant(),
                state.getDateTime().toInstant());
        assertEquals(MarketSentiment.NORMAL, state.getSentiment());
    }
}