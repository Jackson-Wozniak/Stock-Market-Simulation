package org.api.stockmarket.simulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimulationRequest {
    private int days = 1;
    private int momentum = 0;
    private String volatility = "Normal";
    private String rating = "Neutral";
}
