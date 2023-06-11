package org.api.stockmarket.options.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.stocks.stock.entity.Stock;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OptionsChain {

    private Stock stock;
    private String expirationDate;
    private List<OptionContract> putOptions;
    private List<OptionContract> callOptions;
}
