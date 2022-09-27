package org.api.stockmarket.indexfund.model.subclass;

import lombok.Getter;
import lombok.Setter;
import org.api.stockmarket.indexfund.enums.FundTracking;
import org.api.stockmarket.indexfund.model.IndexFund;
import org.api.stockmarket.indexfund.utils.Capitalize;

@Getter
@Setter
public class SectorIndexFund extends IndexFund {

    private String sector;

    public SectorIndexFund(String sector, double price){
        super(Capitalize.capitalize(sector) + " Fund",
                price,
                FundTracking.SECTOR);
        this.sector = sector;
    }
}
