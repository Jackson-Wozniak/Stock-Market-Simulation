package org.api.stocktradingservice.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stocktradingservice.account.model.entity.StockOwned;

@Getter
@Setter
public class StockOwnedDto {

    private String ticker;
    private double amountOwned;
    private double costBasis;

    public StockOwnedDto(StockOwned stockOwned){
        this.ticker = stockOwned.getTicker();
        this.amountOwned = stockOwned.getAmountOwned();
        this.costBasis = stockOwned.getCostBasis();
    }
}
