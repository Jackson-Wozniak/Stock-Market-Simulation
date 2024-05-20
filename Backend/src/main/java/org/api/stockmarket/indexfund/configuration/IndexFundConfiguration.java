package org.api.stockmarket.indexfund.configuration;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.api.stockmarket.indexfund.defaults.DefaultIndexFunds;
import org.api.stockmarket.indexfund.service.IndexFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class IndexFundConfiguration {

    @Autowired
    private final IndexFundService indexFundService;

    @PostConstruct
    public void saveDefaultIndexFundsOnStartup(){
        if(DefaultIndexFunds.getAmountOfIndexFunds() != indexFundService.findIndexFundRowCount()){
            indexFundService.updatePriceForAllFundsDaily();
        }
    }
}
