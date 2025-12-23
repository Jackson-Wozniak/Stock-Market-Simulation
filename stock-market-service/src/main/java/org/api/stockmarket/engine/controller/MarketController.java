package org.api.stockmarket.engine.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.dto.MarketStateDTO;
import org.api.stockmarket.engine.scheduling.MarketSchedulingService;
import org.api.stockmarket.engine.service.MarketStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/market")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MarketController {
    private final MarketStateService marketStateService;
    private final MarketSchedulingService marketSchedulingService;

    @GetMapping()
    public ResponseEntity<MarketStateDTO> getMarketEntity() {
        return ResponseEntity.ok(new MarketStateDTO(marketStateService.findMarketState()));
    }

    @PutMapping("/interval")
    public ResponseEntity<MarketStateDTO> updateInterval(@RequestParam long millis){
        marketSchedulingService.updateInterval(millis);
        return ResponseEntity.ok(new MarketStateDTO(marketStateService.findMarketState()));
    }

    @PutMapping("/pause")
    public ResponseEntity<MarketStateDTO> pauseScheduler(){
        marketSchedulingService.pause();
        return ResponseEntity.ok(new MarketStateDTO(marketStateService.findMarketState()));
    }

    @PutMapping("/resume")
    public ResponseEntity<MarketStateDTO> resumeScheduler(){
        marketSchedulingService.resume();
        return ResponseEntity.ok(new MarketStateDTO(marketStateService.findMarketState()));
    }
}
