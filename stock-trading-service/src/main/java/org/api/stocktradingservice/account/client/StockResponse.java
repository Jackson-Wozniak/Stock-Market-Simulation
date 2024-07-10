package org.api.stocktradingservice.account.client;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class StockResponse {

    @JsonAlias("ticker")
    private String ticker;

    @JsonAlias("companyName")
    private String company;

    @JsonAlias("price")
    private double price;

    @JsonAlias("lastDayPrice")
    private double lastDayPrice;

    @JsonAlias("percentChange")
    private double percentChange;

    @Override
    public String toString() {
        return "StockResponse{" +
                "ticker='" + ticker + '\'' +
                ", company='" + company + '\'' +
                ", price=" + price +
                ", lastDayPrice=" + lastDayPrice +
                ", percentChange=" + percentChange + '\'' +
                '}';
    }
}
