package io.github.stockmarket.simulation.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SimulationSettings {
    private int days;
    private int ticksPerDay;
    private LocalDate startingDate;
}
