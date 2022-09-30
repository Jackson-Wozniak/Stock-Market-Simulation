package org.api.stockmarket.stocks.stock.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercentChangeTest {

    @Test
    void percentChangePositive(){
        assertEquals(100, PercentChange.percentChange(100, 50));
        assertEquals(867.38, PercentChange.percentChange(128.11, 13.243));
    }

    @Test
    void percentChangeNegative(){
        assertEquals(-50, PercentChange.percentChange(50, 100));
        assertEquals(-70.16, PercentChange.percentChange(26.39, 88.45));
    }

    @Test
    void doesNotThrowArithmeticError(){
        assertDoesNotThrow(() -> PercentChange.percentChange(0,0));
    }

}