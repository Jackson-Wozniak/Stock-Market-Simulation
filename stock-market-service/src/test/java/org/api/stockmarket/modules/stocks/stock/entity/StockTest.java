package org.api.stockmarket.modules.stocks.stock.entity;

import org.api.stockmarket.modules.stocks.entity.SmallCapStock;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.api.stockmarket.modules.stocks.enums.InvestorRating;
import org.api.stockmarket.modules.stocks.enums.Volatility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    @Test
    void smallCapPriceChanges(){
        SmallCapStock stock1 = Stock.smallCap("Test 1", "Test 1", "Test 1",
                Volatility.STABLE, InvestorRating.Neutral);
        stock1.setPrice(100.0);
        stock1.setMomentum(0);
        //.003 is multiplier, so max of .3 difference from 100.0
        for(int i = 0; i < 100; i++){
            stock1.updatePrice();
            assertTrue(stock1.getPrice() <= 100.3 && stock1.getPrice() >= 99.7);
            stock1.setPrice(100.0);
        }
    }

    @Test
    void midCapPriceChanges(){

    }

    @Test
    void largeCapPriceChanges(){

    }

    @Test
    void momentumChanges(){

    }

    @Test
    void investorRatings(){

    }

    @Test
    void staticFactoryMethods(){

    }

}