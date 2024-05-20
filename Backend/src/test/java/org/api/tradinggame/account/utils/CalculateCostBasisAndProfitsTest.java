package org.api.tradinggame.account.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCostBasisAndProfitsTest {

    @Test
    void costBasisTestOne() {
        assertEquals(96.67, CalculateCostBasisAndProfits.newCostBasis(
                10, 2, 100, 80));
    }

    @Test
    void costBasisTestTwo() {
        assertEquals(8.79, CalculateCostBasisAndProfits.newCostBasis(
                32, 14, 8.15, 10.25));
    }

    @Test
    void decimalRoundedCorrectly() {
        double notRounded = 98.2223483191843;
        assertEquals(98.22, CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(notRounded));
    }

}