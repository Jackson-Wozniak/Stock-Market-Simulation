package org.api.stockmarket.core.utils;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateConversionTest {

    @Test
    void isLastDayOfMonth(){
        assertTrue(DateConversion.isLastDayMonth(ZonedDateTime.of(
                2025, 3, 31, 0, 0, 0, 0, ZoneId.of("Z")
        )));

        //leap year - 29 days in february
        assertTrue(DateConversion.isLastDayMonth(ZonedDateTime.of(
                2024, 2, 29, 0, 0, 0, 0, ZoneId.of("Z")
        )));
        //leap year - 29 days in february
        assertFalse(DateConversion.isLastDayMonth(ZonedDateTime.of(
                2024, 2, 28, 0, 0, 0, 0, ZoneId.of("Z")
        )));

        //not leap year - 28 days in february
        assertTrue(DateConversion.isLastDayMonth(ZonedDateTime.of(
                2025, 2, 28, 0, 0, 0, 0, ZoneId.of("Z")
        )));
    }

    @Test
    void toFormattedString(){
        ZonedDateTime date = ZonedDateTime.of(2024, 1, 5, 10, 30, 0, 0, ZoneId.of("Z"));

        assertEquals("January 5th, 2024", DateConversion.toFormattedString(date));
    }

    @Test
    void toDashedString(){
        ZonedDateTime date = ZonedDateTime.of(2024, 1, 5, 10, 30, 0, 0, ZoneId.of("Z"));

        assertEquals("1/5/2024", DateConversion.toDashedDate(date.toLocalDate()));
    }
}