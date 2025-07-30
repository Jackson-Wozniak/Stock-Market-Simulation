package org.api.stockmarket.engine.properties;

import org.api.stockmarket.engine.enums.MarketSimulatorMode;

public class MarketEnvironmentProperties {
    public static final MarketSimulatorMode MARKET_MODE = MarketSimulatorMode.SIMULATOR_ONLY;
    //divide price changes by this number to determine scale
    public static final double PRICE_SCALE_DIMINISHER = 1000.0;
}
