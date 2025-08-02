package org.api.stockmarket.engine.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.engine.dtos.MarketState;
import org.api.stockmarket.engine.service.MarketStateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/market")
@AllArgsConstructor
public class MarketController {

    private final MarketStateService marketStateService;

    @GetMapping()
    public ResponseEntity<MarketState> getMarketEntity() {
        return ResponseEntity.ok(new MarketState(marketStateService.findMarketState()));
    }
}
