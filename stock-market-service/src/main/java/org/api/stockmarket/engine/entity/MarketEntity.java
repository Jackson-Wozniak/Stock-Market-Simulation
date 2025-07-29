package org.api.stockmarket.engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.common.utils.DateConversion;
import org.api.stockmarket.engine.enums.MarketTrajectory;
import org.api.stockmarket.engine.enums.TimeStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Month;
import java.time.ZonedDateTime;

//@Entity(name = "market")
//@Table(name = "market")
//@Getter
//@Setter
//@NoArgsConstructor
//public class MarketEntity {
//    @Id
//    private final Long id = 1L;
//
//    @Column(name = "market_date")
//    @Temporal(TemporalType.TIMESTAMP)
//    private ZonedDateTime date;
//
//    @Column(name = "last_month_average_price")
//    private Double lastMonthAveragePrice;
//
//    @Column(name = "market_trajectory")
//    @Enumerated(EnumType.STRING)
//    private MarketTrajectory marketTrajectory;
//
//    private static final Logger logger = LoggerFactory.getLogger(Market.class);
//
//    public TimeStamp increment() {
//        ZonedDateTime newDate = getDate().plusHours(1);
//        setDate(newDate);
//
//        if(newDate.getHour() == 0 && DateConversion.isLastDayMonth(newDate)){
//            logger.info("Start of new day. Today's date is {}", DateConversion.toFormattedString(newDate));
//            return TimeStamp.EndOfMonth;
//        }
//        if(newDate.getHour() == 0){
//            logger.info("Start of new day. Today's date is {}", DateConversion.toFormattedString(newDate));
//            return TimeStamp.EndOfDay;
//        }
//        return TimeStamp.MiddleOfDay;
//    }
//
//    // earnings report released on first day of 3rd, 6th, 9th and 12th month
//    public boolean isEndOfQuarter(){
//        return date.getDayOfMonth() == 1 && date.getMonthValue() % 3 == 0;
//    }
//
//    public boolean isEndOfYear(){
//        return date.getMonth().equals(Month.DECEMBER) && date.getDayOfMonth() == 31;
//    }
//}
