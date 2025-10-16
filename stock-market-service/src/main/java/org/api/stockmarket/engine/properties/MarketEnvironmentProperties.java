package org.api.stockmarket.engine.properties;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MarketEnvironmentProperties {
    public static final boolean PERFORMANCE_DEBUG = true;

    public static final int MARKET_SINGLETON_ID = 1;
    public static final ZonedDateTime STARTING_MARKET_DATE =
            ZonedDateTime.of(LocalDate.of(2025,1,1),
                    LocalTime.of(9, 0), ZoneId.systemDefault());
    public static final MarketSimulatorMode MARKET_MODE = MarketSimulatorMode.FULL_FUNCTIONALITY;
    public static final long MARKET_TIME_INTERVAL = MarketIntervals.ONE_SECOND;
    public static final int PLUS_MINUTES_PER_RUN = 30;
    public static final boolean CSV_ENUMS_USE_STRINGS = false;

    public static final double PRICE_SCALE_DIMINISHER = 800.0;
    public static final int ABSOLUTE_VALUE_FACTOR_RANGE = 50;
    public static final double PRICE_SIGNAL_DIMINISHER = 35.0;

    //Stocks above this price will dampen their price change scales
    public static final double PRICE_CEILING_DAMPENER = 10_000.0;

    public static final double MAX_PERCENT_CHANCE_POSITIVE_NEWS = 5.0;
    public static final double MAX_PERCENT_CHANCE_NEGATIVE_NEWS = 4.0;
    public static final double MIN_PERCENT_CHANCE_POSITIVE_NEWS = 0.25;
    public static final double MIN_PERCENT_CHANCE_NEGATIVE_NEWS = 0.1;
}
