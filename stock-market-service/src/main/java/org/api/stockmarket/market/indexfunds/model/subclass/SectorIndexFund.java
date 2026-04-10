package org.api.stockmarket.market.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.market.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.market.indexfunds.dtos.SectorIndexFundDto;
import org.api.stockmarket.market.indexfunds.enums.FundTracking;
import org.api.stockmarket.market.indexfunds.model.IndexFund;
import org.api.stockmarket.core.utils.Capitalize;

@Getter
@Setter
public class SectorIndexFund extends IndexFund {

    private String sector;

    public SectorIndexFund(String sector, double price) {
        super(Capitalize.capitalize(sector) + " Fund",
                price,
                FundTracking.SECTOR);
        this.sector = sector;
    }

    @Override
    public IndexFundDto toDto(){
        return new SectorIndexFundDto(this);
    }
}
