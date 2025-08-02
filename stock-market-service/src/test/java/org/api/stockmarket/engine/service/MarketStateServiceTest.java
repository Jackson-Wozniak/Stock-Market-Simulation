package org.api.stockmarket.engine.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MarketStateServiceTest {

    @Autowired
    private MarketStateService marketStateService;

    @Test
    void marketEntityIsNotNull() {
        assertNotNull(marketStateService.findMarketEntity());
    }

    @Test
    void marketEntityReturnsTheSameEachTime() {
        Market market = marketStateService.findMarketEntity();
        assertEquals(1, market.getId());
    }

}