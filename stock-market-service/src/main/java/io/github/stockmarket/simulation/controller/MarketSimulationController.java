package io.github.stockmarket.simulation.controller;

import io.github.stockmarket.response.SimulationResponse;
import io.github.stockmarket.simulation.model.SimulationEnvironment;
import io.github.stockmarket.simulation.request.SimulationRequest;
import io.github.stockmarket.simulation.service.MarketSimulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulation")
@AllArgsConstructor
public class MarketSimulationController{
    private final MarketSimulationService marketSimulationService;

    @GetMapping
    public ResponseEntity<SimulationResponse> test(){
        SimulationEnvironment result = marketSimulationService.run(new SimulationRequest());

        return ResponseEntity.ok(new SimulationResponse(result));
    }
}
