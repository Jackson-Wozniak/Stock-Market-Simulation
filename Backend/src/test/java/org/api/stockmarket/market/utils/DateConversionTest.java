package org.api.stockmarket.market.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateConversionTest {

    @Test
    void incrementDate(){
        assertEquals("1/2/1", DateConversion.incrementMarketDay("1/1/1"));
    }

    @Test
    void addToDate(){
        assertEquals("1/6/1", DateConversion.datePlusNDays(5, "1/1/1"));
    }

}