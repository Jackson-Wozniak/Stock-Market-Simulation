package org.api.stockmarket.indexfund.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.helper.CalculateIndexFundPrice;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/funds")
@AllArgsConstructor
public class IndexFundController {

    @Autowired
    private CalculateIndexFundPrice calculateIndexFundPrice;

    @Autowired
    private IndexFundService indexFundService;

    @RequestMapping(value = "/all")
    public List<IndexFund> getAllFunds() {
        return indexFundService.findAllIndexFunds();
    }

    @RequestMapping(value = "/total-market")
    public IndexFund getTotalMarketFund() {
        return indexFundService.findIndexFundByTracker(FundTracking.TOTAL_MARKET)
                .stream().findFirst().orElse(null);
    }

    @RequestMapping(value = "/cap")
    public List<IndexFund> getMarketCapFunds() {
        return indexFundService.findIndexFundByTracker(FundTracking.MARKET_CAP);
    }

    @RequestMapping(value = "/sector")
    public List<IndexFund> getSectorIndexFunds() {
        return indexFundService.findIndexFundByTracker(FundTracking.SECTOR);
    }

    @RequestMapping(value = "/volatility")
    public List<IndexFund> getVolatilityFunds() {
        return indexFundService.findIndexFundByTracker(FundTracking.VOLATILITY);
    }
}