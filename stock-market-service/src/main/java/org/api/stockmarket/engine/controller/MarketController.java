package org.api.stockmarket.engine.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.dtos.MarketState;
import org.api.stockmarket.engine.scheduling.MarketSchedulingService;
import org.api.stockmarket.engine.service.MarketStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {
    private final MarketStateService marketStateService;
    private final MarketSchedulingService marketSchedulingService;

    @GetMapping()
    public ResponseEntity<MarketState> getMarketEntity() {
        return ResponseEntity.ok(new MarketState(marketStateService.findMarketState()));
    }

    @PutMapping("/interval")
    public ResponseEntity<String> updateInterval(@RequestParam long millis){
        marketSchedulingService.updateInterval(millis);
        return ResponseEntity.ok("Updated interval: " + millis + "ms");
    }

    @PutMapping("/pause")
    public ResponseEntity<String> pauseScheduler(){
        marketSchedulingService.pause();
        return ResponseEntity.ok("Simulation paused.");
    }

    @PutMapping("/resume")
    public ResponseEntity<String> resumeScheduler(){
        marketSchedulingService.resume();
        return ResponseEntity.ok("Simulation resumed.");
    }
}
