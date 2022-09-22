package stocktradingsimulator.market.entity;

import lombok.*;
import stocktradingsimulator.market.enums.MarketTrajectory;
import stocktradingsimulator.market.utils.DateConversion;

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
