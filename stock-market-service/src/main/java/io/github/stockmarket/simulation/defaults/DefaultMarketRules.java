package io.github.stockmarket.simulation.defaults;

import io.github.stockmarket.simulation.rules.MarketRules;
import io.github.stockmarket.simulation.rules.PricingFactorRules;
import io.github.stockmarket.simulation.rules.PricingMovementRules;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DefaultMarketRules {

    public static MarketRules createDefault(){
        MarketRules rules = new MarketRules();
        rules.setPricingFactorRules(new PricingFactorRules(ABSOLUTE_VALUE_FACTOR_RANGE));
        rules.setPricingMovementRules(new PricingMovementRules(PRICE_CEILING_DAMPENER, PRICE_SIGNAL_DIMINISHER, PRICE_SCALE_DIMINISHER));

        return rules;
    }

    public static final ZonedDateTime STARTING_MARKET_DATE =
            ZonedDateTime.of(LocalDate.of(2025,1,1),
                    LocalTime.of(9, 0), ZoneId.systemDefault());
    public static final int ADDED_MINUTES_PER_RUN = 30;

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
