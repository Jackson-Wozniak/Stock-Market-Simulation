package org.api.stockmarket.engine.enums;

import lombok.Getter;

/*
Tracks the total market trajectory
Bear Market: Downward Trajectory
Bull Market: Upward Trajectory
Normal Market: No trajectory

This value changes every 30 market days if the average price change exceeded 10%
 */
@Getter
public enum MarketSentiment {
    NORMAL("Normal"),
    BEAR("Bear"),
    BULL("Bull");

    private final String name;

    MarketSentiment(String name){
        this.name = name;
    }
}
