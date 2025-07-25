package org.api.stockmarket.indexfund.dtos;

import lombok.Getter;
import org.api.stockmarket.indexfund.model.IndexFund;

/*
Base class for all IndexFund DTOs. The class 'IndexFund' which is an abstract base class
of IndexFund, contains an abstract method 'toDto' that each child fund implements.
This makes it easy to map a List<IndexFund> of different child types to a List<IndexFundDto> to be returned
 */
@Getter
public class IndexFundDto {
    private final double price;
    private final String name;

    public IndexFundDto(IndexFund fund){
        this.name = fund.getName();
        this.price = fund.getPrice();
    }
}
