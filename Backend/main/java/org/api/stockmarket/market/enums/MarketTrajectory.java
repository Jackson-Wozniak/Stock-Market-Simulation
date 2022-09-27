package org.api.stockmarket.market.enums;

/*
Tracks the total market trajectory
Bear Market: Downward Trajectory
Bull Market: Upward Trajectory
Normal Market: No trajectory

This value changes every 30 market days if the average price change exceeded 10%
 */
public enum MarketTrajectory {
    NORMAL,
    BEAR,
    BULL
}
