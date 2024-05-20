package org.api.stockmarket.market.entity;

import java.time.ZonedDateTime;

import org.api.stockmarket.market.enums.MarketTrajectory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;
    
    @Column
    private Double lastMonthAveragePrice;

    @Column
    @Enumerated(EnumType.STRING)
    private MarketTrajectory marketTrajectory;

    public void increment() {
        ZonedDateTime newDate = getDate().plusHours(1);
        setDate(newDate);
    }
}
