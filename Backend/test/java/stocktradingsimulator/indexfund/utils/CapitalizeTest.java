package stocktradingsimulator.indexfund.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CapitalizeTest {

    @Test
    void stringIsCapitalized(){
        assertEquals("String", Capitalize.capitalize("string"));
    }

    @Test
    void onlyFirstLetterIsCapitalized(){
        assertEquals("String", Capitalize.capitalize("String"));
        assertNotEquals("String", Capitalize.capitalize("STRing"));
        assertNotEquals("String", Capitalize.capitalize("sTrInG"));
    }

}