package io.github.stockmarket.market.stocks.dto;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.stocks.model.Stock;

@Getter
@Setter
public class StockBasicDTO {
    private String ticker;
    private String companyName;
    private double price;

    public StockBasicDTO(Stock stock) {
        this.ticker = stock.getTicker();
        this.companyName = stock.getCompanyAttributes().getCompanyName();
        this.price = stock.getPrice();
    }
}
