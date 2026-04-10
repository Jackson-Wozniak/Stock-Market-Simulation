package io.github.stockmarket.simulation.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingParameters {
    //Stocks above this price will dampen their price change scales
    private double priceCeilingDampener;
    private double priceSignalDiminisher;
    private double priceScaleDiminisher;
    //the range a PricingAttributes factor can be. Example: if 50 then the factor value is [-50, 50]
    private double absoluteValueFactorRange;
}
