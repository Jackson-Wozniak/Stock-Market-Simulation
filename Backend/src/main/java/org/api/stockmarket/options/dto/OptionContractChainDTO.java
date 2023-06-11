package org.api.stockmarket.options.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.stocks.stock.dto.StockDTO;

@Getter
@Setter
public class OptionContractChainDTO {

    private String optionType;
    private double strikePrice;
    private double premium; //actual price is price * 100 as each contract has 100 shares

    public OptionContractChainDTO(OptionContract option, String marketDate){
        this.optionType = option.getOptionType().toString();
        this.strikePrice = option.getStrikePrice();
        this.premium = option.getOptionPremium(marketDate);
    }
}
