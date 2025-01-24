package org.api.stockmarket.indexfund.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.dtos.MarketCapFundDto;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.exception.IndexFundException;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.api.stockmarket.stocks.stock.enums.MarketCap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/funds")
@AllArgsConstructor
public class IndexFundController {

    private IndexFundService indexFundService;

//    @GetMapping
//    public List<IndexFund> getAllFunds() {
//        return indexFundService.findAllIndexFunds();
//    }
//
//    @RequestMapping(value = "/total-market")
//    public IndexFund getTotalMarketFund() {
//        return indexFundService.findIndexFundByTracker(FundTracking.TOTAL_MARKET)
//                .stream().findFirst().orElse(null);
//    }
//
    @RequestMapping(value = "/cap")
    public List<MarketCapFundDto> getAllMarketCapFunds() {
        return indexFundService.findMarketCapFunds().stream()
                .map(fund -> new MarketCapFundDto((MarketCapIndexFund) fund))
                .toList();
    }

    @RequestMapping(value = "/cap/{cap}")
    public MarketCapFundDto getMarketCapFund(@PathVariable String cap) {
        if(MarketCap.map(cap) == null) throw new IndexFundException();
        return new MarketCapFundDto((MarketCapIndexFund) indexFundService.findMarketCapFunds(MarketCap.map(cap)));
    }
//
//    @RequestMapping(value = "/sector")
//    public List<IndexFund> getAllSectorIndexFunds() {
//        return indexFundService.findIndexFundByTracker(FundTracking.SECTOR);
//    }
//
//    @RequestMapping(value = "/sector/{sector}")
//    public IndexFund getSectorIndexFund(@PathVariable String sector) {
//        return indexFundService.findIndexFundByTracker(FundTracking.SECTOR).stream()
//                .filter(fund -> fund.getName().equalsIgnoreCase(sector + " Fund"))
//                .findFirst()
//                .orElseThrow(IndexFundException::new);
//    }
//
//    @RequestMapping(value = "/volatility")
//    public List<IndexFund> getAllVolatilityFunds() {
//        return indexFundService.findIndexFundByTracker(FundTracking.VOLATILITY);
//    }
//
//    @RequestMapping(value = "/volatility/{volatility}")
//    public IndexFund getVolatileIndexFund(@PathVariable String volatility) {
//        return indexFundService.findIndexFundByTracker(FundTracking.VOLATILITY).stream()
//                .filter(fund -> fund.getName().equalsIgnoreCase(volatility + " Index Fund"))
//                .findFirst()
//                .orElseThrow(IndexFundException::new);
//    }
}