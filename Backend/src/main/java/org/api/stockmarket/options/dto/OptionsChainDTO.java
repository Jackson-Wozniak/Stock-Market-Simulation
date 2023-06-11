package org.api.stockmarket.options.dto;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.options.entity.OptionsChain;
import org.api.stockmarket.stocks.stock.dto.StockDTO;
import org.api.stockmarket.stocks.stock.dto.StockSummaryDTO;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OptionsChainDTO {

    private StockSummaryDTO stock;
    private String expirationDate;
    private List<OptionContractChainDTO> calls;
    private List<OptionContractChainDTO> puts;

    public OptionsChainDTO(OptionsChain optionsChain, String marketDate){
        this.stock = new StockSummaryDTO(optionsChain.getStock());
        this.expirationDate = optionsChain.getExpirationDate();
        this.calls = optionsChain.getCallOptions().stream()
                .map(option -> new OptionContractChainDTO(option, marketDate)).
                collect(Collectors.toList());
        this.puts = optionsChain.getPutOptions().stream()
                .map(option -> new OptionContractChainDTO(option, marketDate)).
                collect(Collectors.toList());
    }
}
