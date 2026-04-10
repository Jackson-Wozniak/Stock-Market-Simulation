package io.github.stockmarket.market.indexfunds.model.subclass;

import lombok.Getter;
import lombok.Setter;
import io.github.stockmarket.market.indexfunds.dtos.IndexFundDto;
import io.github.stockmarket.market.indexfunds.dtos.SectorIndexFundDto;
import io.github.stockmarket.market.indexfunds.enums.FundTracking;
import io.github.stockmarket.market.indexfunds.model.IndexFund;
import io.github.stockmarket.core.utils.Capitalize;

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
