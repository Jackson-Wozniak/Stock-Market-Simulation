package org.api.stockmarket.options.controller;

import lombok.AllArgsConstructor;
import org.api.stockmarket.options.entity.OptionContract;
import org.api.stockmarket.options.enums.OptionType;
import org.api.stockmarket.options.service.OptionContractService;
import org.api.stockmarket.stocks.stock.entity.Stock;
import org.api.stockmarket.stocks.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/options")
@AllArgsConstructor
public class OptionContractController {

    @Autowired
    private final OptionContractService optionsService;
    @Autowired
    private final StockService stockService;
    
}
