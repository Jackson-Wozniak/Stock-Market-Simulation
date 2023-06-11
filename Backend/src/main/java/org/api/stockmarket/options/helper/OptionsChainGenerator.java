package org.api.stockmarket.options.helper;

import org.api.stockmarket.market.exception.DateFormatException;
import org.api.stockmarket.market.utils.DateConversion;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.entity.OptionsChain;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.options.exceptions.OptionChainException;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.enums.MarketCap;

import java.util.ArrayList;
import java.util.List;

public class OptionsChainGenerator {

    //Options chains are only offered on large cap stocks (similar to real world options being based on demand)
    /*
    Option chains include 1 dollar increments of up to 10 above and below current stock price
     */
    public static OptionsChain generateChain(Stock stock, String expirationDate){
        chainValidator(stock, expirationDate);
        List<OptionContract> puts = createNextTenContracts(stock, false, expirationDate);
        List<OptionContract> calls = createNextTenContracts(stock, true, expirationDate);

        return new OptionsChain(stock, expirationDate, puts, calls);
    }

    private static void chainValidator(Stock stock, String expirationDate){
        if(!DateConversion.dateIsFormattedCorrectly(expirationDate)){
            throw new DateFormatException();
        }
        if(!stock.getMarketCap().equals(MarketCap.Large)){
            throw new OptionChainException("Only Large Cap stocks can have options chain");
        }
    }

    private static List<OptionContract> createNextTenContracts(Stock stock, boolean isCall, String expiry){
        List<OptionContract> contracts = new ArrayList<>();
        double price = stock.getPrice();
        for(int i = 10; i >= 1; i--){
            double strikePrice = Math.round(price - i);
            contracts.add(new OptionContract(
                    stock,
                    isCall ? OptionType.CALL : OptionType.PUT,
                    expiry,
                    strikePrice
            ));
        }
        for(int i = 1; i <= 10; i++){
            if(price < i) return contracts;
            double strikePrice = Math.round(price + i);
            contracts.add(new OptionContract(
                    stock,
                    isCall ? OptionType.CALL : OptionType.PUT,
                    expiry,
                    strikePrice
            ));
        }
        return contracts;
    }
}
