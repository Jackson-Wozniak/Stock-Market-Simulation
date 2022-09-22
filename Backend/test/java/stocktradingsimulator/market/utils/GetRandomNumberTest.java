package stocktradingsimulator.market.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetRandomNumberTest {

    @Test
    void randomNumberShouldBeWithinBounds(){
        double randomLargeCapNumber = GetRandomNumber.getRandomNumberForStocks("large");
        double randomMidCapNumber = GetRandomNumber.getRandomNumberForStocks("mid");
        double randomSmallCapNumber = GetRandomNumber.getRandomNumberForStocks("small");

        assert(randomLargeCapNumber > -.002 && randomLargeCapNumber < .002);
        assert(randomMidCapNumber > -.001 && randomMidCapNumber < .001);
        assert(randomSmallCapNumber > -.003 && randomSmallCapNumber < .003);
        assert(GetRandomNumber.getRandomNumberForStocks("") == -1);
    }

    @Test
    void randomNumberShouldAllowCapitalization(){
        assertNotEquals(-1, GetRandomNumber.getRandomPositiveNumberForStocks("LARGE"));
        assertNotEquals(-1, GetRandomNumber.getRandomPositiveNumberForStocks("MId"));
        assertNotEquals(-1, GetRandomNumber.getRandomPositiveNumberForStocks("smALL"));
    }

    @Test
    void randomPositiveNumbersShouldBeWithinBounds(){
        double randomLargeCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks("large");
        double randomMidCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks("mid");
        double randomSmallCapNumber = GetRandomNumber.getRandomPositiveNumberForStocks("small");

        assert(randomLargeCapNumber > 0 && randomLargeCapNumber < .002);
        assert(randomMidCapNumber > 0 && randomMidCapNumber < .001);
        assert(randomSmallCapNumber > 0 && randomSmallCapNumber < .003);
        assert(GetRandomNumber.getRandomNumberForStocks("") == -1);
    }

}