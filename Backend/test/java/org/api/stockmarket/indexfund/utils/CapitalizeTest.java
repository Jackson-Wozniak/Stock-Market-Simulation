package org.api.stockmarket.indexfund.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTest {

    @Test
    void stringIsCapitalized() {
        assertEquals("String", Capitalize.capitalize("string"));
    }

    @Test
    void onlyFirstLetterIsCapitalized() {
        assertEquals("String", Capitalize.capitalize("String"));
        assertEquals("String", Capitalize.capitalize("STRing"));
        assertEquals("String", Capitalize.capitalize("sTrInG"));
    }

}