package io.github.stockmarket.simulation.enums;

/*
The goal of EconomicEnvironment is to dictate which types of pricing factors have more impact. This is not currently
modeled in the pricing movement, however the goal would be examples such as:
- recession: general downtrend, but less negative impact with good fundamentals
- contraction: more hesitancy with short term sentiment or innovation/growth factors
- neutral: standard pricing movement
- recovery: 'timid' optimism, especially around speculative and innovation/growth factors
- boom: general uptrend, especially beneficial toward high growth factors

An easy approach to implementing EconomicEnvironment with the pricing movement model is to just increase/decrease
the 'effective' factor value of certain factors depending on the economic environment. That way it affects all stocks,
but scales faster for stocks already weighted toward the specific environments factor impact
 */
public enum EconomicEnvironment {
    RECESSION,
    CONTRACTION,
    NEUTRAL,
    RECOVERY,
    BOOM
}
