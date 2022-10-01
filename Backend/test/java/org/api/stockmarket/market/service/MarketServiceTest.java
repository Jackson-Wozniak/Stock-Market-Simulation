package org.api.stockmarket.market.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.api.stockmarket.market.entity.Market;
import org.api.stockmarket.market.service.MarketService;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    void marketEntityIsNotNull() {
        assertNotNull(marketService.findMarketEntity());
    }

    @Test
    void marketEntityReturnsTheSameEachTime() {
        Market market = marketService.findMarketEntity();
        assertEquals(1, market.getId());
    }

}