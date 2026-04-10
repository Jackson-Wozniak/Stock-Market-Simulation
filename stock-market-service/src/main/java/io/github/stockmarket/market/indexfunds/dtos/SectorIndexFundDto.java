package io.github.stockmarket.market.indexfunds.dtos;

import lombok.Getter;
import io.github.stockmarket.market.indexfunds.model.subclass.SectorIndexFund;

@Getter
public class SectorIndexFundDto extends IndexFundDto{

    private final String sector;

    public SectorIndexFundDto(SectorIndexFund fund){
        super(fund);
        this.sector = fund.getSector();
    }
}
