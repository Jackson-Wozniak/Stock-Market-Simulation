package io.github.stockmarket.simulation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimulationSettings {
    private int days;
    private int ticksPerDay;
    private LocalDate startingDate;
}
