package stocktradingsimulator.market.entity;

import lombok.*;
import stocktradingsimulator.market.enums.MarketTrajectory;

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

    @Column
    private Double lastMonthAveragePrice;

    @Column
    private MarketTrajectory marketTrajectory;
}
