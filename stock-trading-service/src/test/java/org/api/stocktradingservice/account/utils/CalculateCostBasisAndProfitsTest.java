package org.api.stocktradingservice.account.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculateCostBasisAndProfitsTest {

    @Test
    void costBasisTestOne() {
        Assertions.assertEquals(96.67, CalculateCostBasisAndProfits.newCostBasis(
                10, 2, 100, 80));
    }

    @Test
    void costBasisTestTwo() {
        Assertions.assertEquals(8.79, CalculateCostBasisAndProfits.newCostBasis(
                32, 14, 8.15, 10.25));
    }

    @Test
    void decimalRoundedCorrectly() {
        double notRounded = 98.2223483191843;
        Assertions.assertEquals(98.22, CalculateCostBasisAndProfits.roundToTwoDecimalPlaces(notRounded));
    }

}