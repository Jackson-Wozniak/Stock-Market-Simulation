package org.api.stockmarket.engine.properties;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;

public class MarketEnvironmentProperties {
    public static final MarketSimulatorMode MARKET_MODE = MarketSimulatorMode.SIMULATOR_ONLY;
    public static final double PRICE_SCALE_DIMINISHER = 1000.0;
    public static final long MARKET_TIME_INTERVAL = MarketIntervals.TEN_SECONDS;
    public static final boolean CSV_ENUMS_USE_STRINGS = false;
}
