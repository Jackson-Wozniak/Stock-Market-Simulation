package io.github.stockmarket.simulation.rules;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingMovementRules {
    //Stocks above this price will dampen their price change scales
    private double priceCeilingDampener;
    private double priceSignalDiminisher;
    private double priceScaleDiminisher;
}
