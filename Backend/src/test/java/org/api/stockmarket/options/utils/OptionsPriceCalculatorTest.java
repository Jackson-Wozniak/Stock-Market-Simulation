package org.api.stockmarket.options.utils;

import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsPriceCalculatorTest {

    @Test
    void callPriceCalculation() {
        OptionContract optionContract = new OptionContract(
                defaultStock(),
                OptionType.CALL,
                "1/5/1",
                105
        );
        optionContract.getStock().setPrice(100.0);
        assertEquals(.11, OptionsPriceCalculator.blackScholesFormula(optionContract, "1/1/1"));

        optionContract.getStock().setVolatileStock(Volatility.VOLATILE);
        assertEquals(.03, OptionsPriceCalculator.blackScholesFormula(optionContract, "1/1/1"));
    }

    @Test
    void putOptionCalculator() {
        OptionContract optionContract = new OptionContract(
                defaultStock(),
                OptionType.PUT,
                "1/5/1",
                105
        );
        optionContract.getStock().setPrice(100.0);
        assertEquals(5.07, OptionsPriceCalculator.blackScholesFormula(optionContract, "1/1/1"));

        optionContract.getStock().setVolatileStock(Volatility.VOLATILE);
        assertEquals(4.99, OptionsPriceCalculator.blackScholesFormula(optionContract, "1/1/1"));
    }

    public static Stock defaultStock(){
        return new Stock(
                "Test",
                "Test",
                "Test",
                MarketCap.Large,
                Volatility.EXTRA_VOLATILE,
                InvestorRating.Neutral
        );
    }
}