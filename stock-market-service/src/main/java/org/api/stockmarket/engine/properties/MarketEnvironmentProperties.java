package org.api.stockmarket.engine.properties;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;

public class MarketEnvironmentProperties {
    public static final MarketSimulatorMode MARKET_MODE = MarketSimulatorMode.FULL_FUNCTIONALITY;
    public static final long MARKET_TIME_INTERVAL = MarketIntervals.TEN_SECONDS;
    public static final boolean CSV_ENUMS_USE_STRINGS = false;

    public static final double PRICE_SCALE_DIMINISHER = 800.0;
    public static final int ABSOLUTE_VALUE_FACTOR_RANGE = 50;
    public static final double PRICE_SIGNAL_DIMINISHER = 35.0;

    //Stocks above this price will dampen their price change scales
    public static final double PRICE_CEILING_DAMPENER = 10_000.0;
}
