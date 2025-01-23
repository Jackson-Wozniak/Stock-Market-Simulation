package org.api.stockmarket.indexfund.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;

@Getter
@Setter
@AllArgsConstructor
public abstract class IndexFund {

    private String name;
    private double price;
    private FundTracking fundTracking;
}
