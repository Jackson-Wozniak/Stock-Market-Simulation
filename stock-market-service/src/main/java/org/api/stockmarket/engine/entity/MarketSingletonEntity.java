package org.api.stockmarket.engine.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.api.stockmarket.engine.enums.MarketSentiment;
import org.api.stockmarket.engine.properties.MarketEnvironmentProperties;

import java.time.Month;
import java.time.ZonedDateTime;

@Entity(name = "market")
@Table(name = "market")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarketSingletonEntity {
    @Id
    private final Long id = 1L;

    @Column(name = "market_date")
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime date;

    @Column(name = "market_sentiment")
    @Enumerated(EnumType.STRING)
    private MarketSentiment sentiment;

    private MarketSingletonEntity(ZonedDateTime date, MarketSentiment sentiment){
        this.date = date;
        this.sentiment = sentiment;
    }

    public static MarketSingletonEntity getDefault(){
        return new MarketSingletonEntity(
                MarketEnvironmentProperties.STARTING_MARKET_DATE,
                MarketSentiment.NORMAL
        );
    }

    public MarketState getState(){
        return new MarketState(date, sentiment);
    }

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

    // earnings report released on first day of 3rd, 6th, 9th and 12th month
    public boolean isEndOfQuarter(){
        return date.getDayOfMonth() == 1 && date.getMonthValue() % 3 == 0;
    }

    public boolean isEndOfYear(){
        return date.getMonth().equals(Month.DECEMBER) && date.getDayOfMonth() == 31;
    }
}
