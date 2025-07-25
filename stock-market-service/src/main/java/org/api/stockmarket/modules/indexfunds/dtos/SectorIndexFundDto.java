package org.api.stockmarket.indexfund.dtos;

import lombok.Getter;
import org.api.stockmarket.indexfund.model.subclass.SectorIndexFund;

@Getter
public class SectorIndexFundDto extends IndexFundDto{

    private final String sector;

    public SectorIndexFundDto(SectorIndexFund fund){
        super(fund);
        this.sector = fund.getSector();
    }
}
