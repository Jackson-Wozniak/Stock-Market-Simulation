package org.api.stockmarket.options.service;

import lombok.AllArgsConstructor;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.repository.OptionContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionContractService {

    @Autowired
    private final OptionContractRepository optionsRepository;

    public OptionContract saveOptionContract(OptionContract optionContract){
        return optionsRepository.save(optionContract);
    }

    public List<OptionContract> findAllActiveContracts(){
        return optionsRepository.findAll();
    }
}
