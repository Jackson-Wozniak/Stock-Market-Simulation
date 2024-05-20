package org.api.stockmarket.market.entity;

import java.time.Month;
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
import org.api.stockmarket.market.enums.TimeStamp;
import org.api.stockmarket.market.utils.DateConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(Market.class);

    public TimeStamp increment() {
        ZonedDateTime newDate = getDate().plusHours(1);
        setDate(newDate);

        if(newDate.getHour() == 0 && DateConversion.isLastDayMonth(newDate)){
            logger.info("Start of new day. Today's date is {}", DateConversion.toFormattedString(newDate));
            return TimeStamp.EndOfMonth;
        }
        if(newDate.getHour() == 0){
            logger.info("Start of new day. Today's date is {}", DateConversion.toFormattedString(newDate));
            return TimeStamp.EndOfDay;
        }
        return TimeStamp.MiddleOfDay;
    }

    // earnings report released on first day of 3rd, 6th, 9th and 12th month
    public boolean isEndOfQuarter(){
        return date.getDayOfMonth() == 1 && date.getMonthValue() % 3 == 0;
    }

    public boolean isEndOfYear(){
        return date.getMonth().equals(Month.DECEMBER) && date.getDayOfMonth() == 31;
    }
}
