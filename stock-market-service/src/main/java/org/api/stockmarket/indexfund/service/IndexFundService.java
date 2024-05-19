package org.api.stockmarket.indexfund.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.helper.UpdateIndexFundPrices;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.repository.IndexFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IndexFundService {

    @Autowired
    private final IndexFundRepository indexFundRepository;
    @Autowired
    private final UpdateIndexFundPrices updateIndexFundPrices;

    public void updatePriceForAllFundsDaily(){
        updateIndexFundPrices.updateMarketCapIndexFunds().forEach(indexFundRepository::save);
        updateIndexFundPrices.updateSectorIndexFunds().forEach(indexFundRepository::save);
        indexFundRepository.save(updateIndexFundPrices.updateStableIndexFund());
        indexFundRepository.save(updateIndexFundPrices.updateVolatileIndexFund());
        indexFundRepository.save(updateIndexFundPrices.updateTotalMarketIndexFund());
    }

    public List<IndexFund> findAllIndexFunds(){
        return indexFundRepository.findAll();
    }

    public List<IndexFund> findIndexFundByTracker(FundTracking fundTracking){
        return indexFundRepository.findAll().stream()
                .filter(fund -> fund.getFundTracking() == fundTracking)
                .collect(Collectors.toList());
    }

    public long findIndexFundRowCount(){
        return indexFundRepository.count();
    }
}
