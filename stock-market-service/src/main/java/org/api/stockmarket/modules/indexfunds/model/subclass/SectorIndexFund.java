package org.api.stockmarket.modules.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.modules.indexfunds.dtos.IndexFundDto;
import org.api.stockmarket.modules.indexfunds.dtos.SectorIndexFundDto;
import org.api.stockmarket.modules.indexfunds.enums.FundTracking;
import org.api.stockmarket.modules.indexfunds.model.IndexFund;
import org.api.stockmarket.modules.indexfunds.utils.Capitalize;

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
