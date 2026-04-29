package io.github.stockmarket.simulation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.stockmarket.simulation.defaults.DefaultMarketRules;
import io.github.stockmarket.simulation.mapper.MarketRulesMapper;
import io.github.stockmarket.simulation.mapper.SimulationSettingsMapper;
import io.github.stockmarket.simulation.request.MarketRulesRequest;
import io.github.stockmarket.simulation.request.PricingFactorRulesRequest;
import io.github.stockmarket.simulation.request.PricingMovementRulesRequest;
import io.github.stockmarket.simulation.rules.MarketRules;
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
    private final MarketRulesMapper mapper;
    private final SimulationSettingsMapper simulationSettingsMapper;

    @GetMapping
    public ResponseEntity<Double> test() throws JsonProcessingException {
        MarketRules rules = DefaultMarketRules.createDefault();
        MarketRulesRequest request = new MarketRulesRequest();
        request.setPricingMovementRules(new PricingMovementRulesRequest(1, 1, 2.0));
        request.setPricingFactorRules(new PricingFactorRulesRequest(500));
        mapper.updateMarketRules(request, rules);

        return ResponseEntity.ok(rules.getPricingFactorRules().getAbsoluteValueRange());
    }
}
