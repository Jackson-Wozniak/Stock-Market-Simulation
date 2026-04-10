package org.api.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.stocks.model.Stock;

@Getter
@Setter
public class StockBasicDTO {
    private String ticker;
    private String companyName;
    private double price;

    public StockBasicDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompany().getCompanyName();
        this.price = stock.getPrice();
    }
}
