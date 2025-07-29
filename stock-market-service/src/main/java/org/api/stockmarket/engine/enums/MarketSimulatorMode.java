package org.api.stockmarket.engine.enums;

//Determines whether the stocks in the DB change, or only available through
//the simulated market controller
public enum MarketSimulatorMode {
    FULL_FUNCTIONALITY,
    SIMULATOR_ONLY,
    DATABASE_ONLY
}
