package org.api.stockmarket.indexfund.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.defaults.DefaultIndexFunds;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.helper.CalculateIndexFundPrice;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.model.subclass.MarketCapIndexFund;
import org.api.stockmarket.indexfund.repository.IndexFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class IndexFundService {

    @Autowired
    private final IndexFundRepository indexFundRepository;
    @Autowired
    private final CalculateIndexFundPrice calculateIndexFundPrice;

    public void updatePriceForAllFundsDaily(){
        calculateIndexFundPrice.updateMarketCapIndexFunds().forEach(indexFundRepository::save);
        calculateIndexFundPrice.updateSectorIndexFunds().forEach(indexFundRepository::save);
    }

    public List<IndexFund> findAllIndexFunds(){
        return indexFundRepository.findAll();
    }
}
