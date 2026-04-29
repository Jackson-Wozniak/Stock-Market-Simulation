package io.github.stockmarket.simulation.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SimulationSettingsRequest {
    private int days;
    private int ticksPerDay;
    private LocalDate startingDate;
}
