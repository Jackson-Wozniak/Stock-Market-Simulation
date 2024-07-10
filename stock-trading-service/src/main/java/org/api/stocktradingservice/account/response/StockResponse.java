package org.api.stocktradingservice.account.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockResponse {

    private String ticker;
    private String companyName;
    private double price;
    private double lastDayPrice;
    private double percentChange;

    @Override
    public String toString() {
        return "StockResponse{" +
                "ticker='" + ticker + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                ", lastDayPrice=" + lastDayPrice +
                ", percentChange=" + percentChange +
                '}';
    }
}
