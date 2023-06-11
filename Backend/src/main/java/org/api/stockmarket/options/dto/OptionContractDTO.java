package org.api.stockmarket.options.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.stocks.stock.dto.StockDTO;
import org.api.stockmarket.stocks.stock.entity.Stock;

@Getter
@Setter
public class OptionContractDTO {

    private long id;
    private StockDTO stock;
    private String optionType;
    private String expirationDate;
    private double strikePrice;
    private double price; //actual price is price * 100 as each contract has 100 shares

    public OptionContractDTO(OptionContract option, String marketDate){
        this.id = option.getId();
        this.stock = new StockDTO(option.getStock());
        this.optionType = option.getOptionType().toString();
        this.expirationDate = option.getExpirationDate();
        this.strikePrice = option.getStrikePrice();
        this.price = option.getOptionPremium(marketDate);
    }
}
