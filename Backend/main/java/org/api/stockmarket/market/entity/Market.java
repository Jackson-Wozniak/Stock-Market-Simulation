package org.api.stockmarket.market.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.api.stockmarket.market.utils.DateConversion;
import org.api.stockmarket.market.enums.MarketTrajectory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Market {

    @Id
    @JsonIgnore
    private final Integer id = 1;

    //formatted as month/day/year, starts at 1 for each
    @Column
    private String date;

    @Column
    private Double lastMonthAveragePrice;

    @Column
    private MarketTrajectory marketTrajectory;

    public void incrementDay(){
        String date = DateConversion.incrementMarketDay(getDate());
        setDate(date);
    }
}
