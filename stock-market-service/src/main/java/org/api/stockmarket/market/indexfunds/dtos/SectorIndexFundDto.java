package org.api.stockmarket.market.indexfunds.dtos;

import lombok.Getter;
import org.api.stockmarket.market.indexfunds.model.subclass.SectorIndexFund;

@Getter
public class SectorIndexFundDto extends IndexFundDto{

    private final String sector;

    public SectorIndexFundDto(SectorIndexFund fund){
        super(fund);
        this.sector = fund.getSector();
    }
}
