package org.api.stocktradingservice.account.client;

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

    private String ticker;
    private String company;
    private String sector;
    private String marketCap;
    private double price;
    private double lastDayPrice;
    private int momentum;
    private int momentumStreakInDays;
    private String volatileStock;
    private String investorRating;
}
