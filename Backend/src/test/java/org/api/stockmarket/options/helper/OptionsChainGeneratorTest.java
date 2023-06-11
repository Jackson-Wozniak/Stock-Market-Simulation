package org.api.stockmarket.options.helper;

import org.api.stockmarket.market.exception.DateFormatException;
import org.api.stockmarket.options.entity.OptionsChain;
import org.api.stockmarket.options.exceptions.OptionChainException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.InvestorRating;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.api.stockmarket.stocks.stock.enums.Volatility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsChainGeneratorTest {

    @Test
    void validStrikePrices(){
        Stock stock = new Stock("Test", "Test", "Test", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral);
        stock.setPrice(100.0);
        OptionsChain chain = OptionsChainGenerator.generateChain(stock, "1/1/1");
        assertEquals(stock, chain.getStock());
        for(int i = 1; i <= 10; i++){
            assertEquals(stock.getPrice() - (11 - i),chain.getCallOptions().get(i - 1).getStrikePrice());
            assertEquals(stock.getPrice() - (11 -i),chain.getPutOptions().get(i - 1).getStrikePrice());
        }
        for(int i = 1; i <= 10; i++){
            assertEquals(stock.getPrice() + i,chain.getCallOptions().get(i + 9).getStrikePrice());
            assertEquals(stock.getPrice() + i,chain.getPutOptions().get(i + 9).getStrikePrice());
        }
    }

    @Test
    void marketCapChecker(){
        Stock smallCap = new Stock("Test", "Test", "Test", MarketCap.Small, Volatility.NORMAL, InvestorRating.Neutral);
        Stock midCap = new Stock("Test", "Test", "Test", MarketCap.Mid, Volatility.NORMAL, InvestorRating.Neutral);
        assertThrows(OptionChainException.class,
                () -> OptionsChainGenerator.generateChain(smallCap, "1/1/1"));
        assertThrows(OptionChainException.class,
                () -> OptionsChainGenerator.generateChain(midCap, "1/1/1"));
    }

    @Test
    void dateChecker(){
        Stock midCap = new Stock("Test", "Test", "Test", MarketCap.Large, Volatility.NORMAL, InvestorRating.Neutral);
        assertThrows(DateFormatException.class,
                () -> OptionsChainGenerator.generateChain(midCap, "111"));
    }
}